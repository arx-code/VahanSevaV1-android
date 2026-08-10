# GitHub MCP Setup Guide

## ✅ What's Been Installed

The GitHub MCP (Model Context Protocol) server has been installed and configured in your Claude Desktop config. This allows me to interact with GitHub directly from our conversation.

## 🔑 Step 1: Create GitHub Personal Access Token

You need to create a GitHub Personal Access Token (PAT) to enable the MCP connection.

### Option A: Classic Token (Recommended for simplicity)

1. Go to [GitHub Settings → Developer Settings → Personal Access Tokens → Tokens (classic)](https://github.com/settings/tokens)

2. Click **"Generate new token"** → **"Generate new token (classic)"**

3. Configure the token:
   - **Note**: `Claude Code - VahanSeva Project`
   - **Expiration**: Choose your preference (90 days recommended)
   - **Select scopes** (check these boxes):
     - ✅ `repo` (Full control of private repositories)
     - ✅ `workflow` (Update GitHub Action workflows)
     - ✅ `admin:org` (if working with organization repos)
     - ✅ `project` (Full control of projects)
     - ✅ `gist` (Create gists)

4. Click **"Generate token"** at the bottom

5. **IMPORTANT**: Copy the token immediately (you won't see it again!)
   - It looks like: `ghp_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx`

### Option B: Fine-grained Token (More secure)

1. Go to [GitHub Settings → Developer Settings → Personal Access Tokens → Fine-grained tokens](https://github.com/settings/tokens?type=beta)

2. Click **"Generate new token"**

3. Configure:
   - **Token name**: `Claude Code - VahanSeva`
   - **Expiration**: 90 days or custom
   - **Resource owner**: Your account
   - **Repository access**: 
     - Choose "All repositories" OR
     - "Only select repositories" → Select VahanSevaV1 (after you create it)

4. **Permissions** (Repository permissions):
   - ✅ **Contents**: Read and write
   - ✅ **Issues**: Read and write
   - ✅ **Pull requests**: Read and write
   - ✅ **Workflows**: Read and write
   - ✅ **Metadata**: Read-only (auto-selected)

5. Click **"Generate token"** and copy it

---

## 🔧 Step 2: Add Token to Configuration

Now update your `.claude/claude_desktop_config.json` with the token:

### Manual Method:

1. Open `.claude/claude_desktop_config.json`

2. Find this section:
```json
"github": {
  "command": "node",
  "args": [...],
  "env": {
    "GITHUB_PERSONAL_ACCESS_TOKEN": "your_github_token_here"
  }
}
```

3. Replace `"your_github_token_here"` with your actual token:
```json
"GITHUB_PERSONAL_ACCESS_TOKEN": "ghp_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
```

4. Save the file

### Using Command (I can do this for you):

Just tell me: **"Update the GitHub token to [your-token]"** and I'll update it for you.

---

## 🔄 Step 3: Restart Claude Desktop

**IMPORTANT**: After adding the token, you must restart Claude Desktop for the MCP to connect.

1. Close Claude Desktop completely
2. Reopen Claude Desktop
3. The GitHub MCP will now be active

---

## ✨ Step 4: Verify Connection

After restarting, I'll be able to:

- ✅ Create repositories
- ✅ Push code
- ✅ Create branches
- ✅ Open pull requests
- ✅ Create issues
- ✅ Manage repository settings
- ✅ View repository contents
- ✅ Create and manage releases

You can test by asking me:
- **"Create a GitHub repository for VahanSeva"**
- **"List my GitHub repositories"**
- **"Create an issue for the login screen"**

---

## 🛠️ What We've Configured

### Current MCP Servers:

1. **android-mcp** - Android development tools
2. **github** (NEW) - GitHub integration

### Configuration Location:
```
C:\Users\khan\.claude\claude_desktop_config.json
```

### GitHub MCP Features:

- Create/delete repositories
- Manage branches
- Create/update/close issues
- Create/merge pull requests
- Push commits
- Manage files
- Create releases
- Search repositories
- Fork repositories

---

## 🔒 Security Notes

- **Never share your Personal Access Token** with anyone
- The token is stored locally on your machine
- If compromised, immediately revoke it on GitHub and generate a new one
- Use the minimum required permissions
- Set an expiration date for security

---

## 🚨 Troubleshooting

### If MCP doesn't work after restart:

1. **Verify token is correct** (no extra spaces/quotes)
2. **Check token hasn't expired** on GitHub
3. **Verify Node.js path** is correct:
   ```bash
   which node
   npm list -g @modelcontextprotocol/server-github
   ```
4. **Check Claude Desktop logs** for errors

### To reinstall GitHub MCP:
```bash
npm uninstall -g @modelcontextprotocol/server-github
npm install -g @modelcontextprotocol/server-github
```

---

## 📋 Quick Setup Checklist

- [x] GitHub MCP server installed
- [x] Configuration file updated
- [ ] Generate GitHub Personal Access Token
- [ ] Add token to `.claude/claude_desktop_config.json`
- [ ] Restart Claude Desktop
- [ ] Test GitHub connection

---

## 🎯 Next Steps After MCP Setup

Once GitHub MCP is active, we can:

1. **Create VahanSeva repository** on GitHub
2. **Push all commits** automatically
3. **Create issues** for each feature in the roadmap
4. **Set up project board** for task tracking
5. **Create branches** for feature development
6. **Open pull requests** for code review

---

## 📞 Need Help?

Just ask me:
- "How do I create a GitHub token?"
- "Update my GitHub token"
- "Test GitHub MCP connection"
- "Create VahanSeva repository on GitHub"

---

**Status**: GitHub MCP installed, waiting for Personal Access Token  
**Last Updated**: August 10, 2026 (14:13 UTC)
