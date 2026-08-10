# GitHub Setup Instructions for VahanSeva

## Step 1: Create GitHub Repository

1. Go to [GitHub](https://github.com) and sign in
2. Click the **"+"** icon in the top right → **"New repository"**
3. Fill in the details:
   - **Repository name**: `VahanSevaV1` or `vahanseva-android`
   - **Description**: "Used car marketplace platform - Android app with Kotlin, Jetpack Compose, and MVVM architecture"
   - **Visibility**: Choose Public or Private
   - **DO NOT** initialize with README, .gitignore, or license (we already have these)
4. Click **"Create repository"**

## Step 2: Connect Local Repository to GitHub

After creating the repository, GitHub will show you commands. Use these:

```bash
# Add the remote repository
git remote add origin https://github.com/YOUR_USERNAME/VahanSevaV1.git

# Verify the remote was added
git remote -v

# Push your code to GitHub
git push -u origin master
```

Replace `YOUR_USERNAME` with your actual GitHub username.

## Step 3: Verify Upload

1. Refresh your GitHub repository page
2. You should see all your files, including:
   - `README.md`
   - `DEVELOPMENT.md`
   - `CLAUDE.md`
   - `app/` directory with your code
   - `.gitignore`

## Step 4: Set Up Branch Protection (Optional but Recommended)

1. Go to repository **Settings** → **Branches**
2. Add branch protection rule for `master` or `main`:
   - Require pull request reviews before merging
   - Require status checks to pass
   - Include administrators

## Step 5: Add Collaborators (If Team Project)

1. Go to **Settings** → **Collaborators**
2. Click **"Add people"**
3. Enter GitHub usernames or email addresses

## GitHub CLI Installation (Optional)

For easier GitHub operations from terminal:

### Windows (using winget)
```bash
winget install --id GitHub.cli
```

### Windows (using Chocolatey)
```bash
choco install gh
```

### After installation
```bash
# Authenticate
gh auth login

# Create repository from CLI (alternative to web UI)
gh repo create VahanSevaV1 --public --source=. --remote=origin --push
```

## Useful Git Commands

```bash
# Check current status
git status

# View commit history
git log --oneline

# Create a new branch for features
git checkout -b feature/user-authentication

# Push new branch to GitHub
git push -u origin feature/user-authentication

# View all branches
git branch -a

# Switch branches
git checkout master

# Pull latest changes from GitHub
git pull origin master
```

## Git Workflow Best Practices

### Feature Branch Workflow
1. Create a new branch for each feature:
   ```bash
   git checkout -b feature/car-listing-screen
   ```

2. Make changes and commit:
   ```bash
   git add .
   git commit -m "Add car listing screen with filters"
   ```

3. Push to GitHub:
   ```bash
   git push -u origin feature/car-listing-screen
   ```

4. Create Pull Request on GitHub
5. Review and merge to master

### Commit Message Format
```
<type>: <short summary>

<optional detailed description>

Co-Authored-By: Claude <noreply@anthropic.com>
```

**Types**: `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`

**Examples**:
- `feat: Add user authentication with JWT`
- `fix: Resolve crash on empty car list`
- `docs: Update API integration guide`
- `refactor: Simplify CarRepository logic`

## Next Steps After GitHub Setup

1. **Add GitHub repository URL** to `CLAUDE.md` and `README.md`
2. **Set up CI/CD** with GitHub Actions (optional)
3. **Create issues** for each screen/feature to implement
4. **Add project board** for task tracking
5. **Enable Dependabot** for dependency updates

## GitHub Actions CI/CD (Optional)

Create `.github/workflows/android-ci.yml`:

```yaml
name: Android CI

on:
  push:
    branches: [ master ]
  pull_request:
    branches: [ master ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 11
      uses: actions/setup-java@v3
      with:
        java-version: '11'
        distribution: 'temurin'
    - name: Grant execute permission for gradlew
      run: chmod +x gradlew
    - name: Build with Gradle
      run: ./gradlew build
    - name: Run tests
      run: ./gradlew test
```

---

**Your current commit history:**
```
f60dc62 - Update Claude settings
b92b8fd - Add CLAUDE.md: Project documentation and development guidelines
5d41f6f - Initial commit: VahanSeva Android project setup
```

You're ready to push to GitHub! 🚀
