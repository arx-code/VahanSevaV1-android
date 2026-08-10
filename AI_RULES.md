# AI Agent Rules - Vahan Seva Auto-Mall

**Version**: 1.0.0  
**Date**: August 10, 2026  
**Purpose**: Ensure project continuity across any coding AI agent

---

## Core Principle

**This project must NOT depend on any single AI coding agent.**

If Claude Code stops working, you must be able to switch to Cursor, Gemini, Copilot, or any other AI agent and continue development seamlessly.

---

## Rule 1: Repository is Source of Truth

**All important project knowledge lives in version-controlled files.**

### What Must Be Documented
- ✅ Product requirements
- ✅ Architectural decisions
- ✅ Design system and components
- ✅ Data models and schemas
- ✅ Navigation flows
- ✅ Implementation status
- ✅ Decisions and rationale

### What Must NOT Be Hidden
- ❌ Important decisions only in AI memory
- ❌ Context only in conversation history
- ❌ Undocumented assumptions
- ❌ Design choices not in code/docs

### Critical Documents
1. **PRODUCT_REQUIREMENTS.md** - What to build
2. **ANDROID_ARCHITECTURE.md** - How to build
3. **DESIGN_SYSTEM.md** - Visual/component specs
4. **DATA_MODEL.md** - Database schema
5. **NAVIGATION_MAP.md** - Screen flows
6. **COMPONENT_INVENTORY.md** - Reusable components
7. **MOCK_DATA_SPECIFICATION.md** - Test data structure
8. **IMPLEMENTATION_PLAN.md** - Step-by-step roadmap
9. **DEVELOPMENT_STATUS.md** - Current progress
10. **CLAUDE.md** - Quick reference

---

## Rule 2: Read Before Changing

**Before writing ANY code, inspect existing code and documentation.**

### Before Adding a Screen
1. ✅ Read NAVIGATION_MAP.md
2. ✅ Read existing screen implementations
3. ✅ Check COMPONENT_INVENTORY.md for reusable components
4. ✅ Follow existing patterns

### Before Creating a Component
1. ✅ Check COMPONENT_INVENTORY.md
2. ✅ Check DESIGN_SYSTEM.md
3. ✅ Inspect existing similar components
4. ✅ Don't duplicate

### Before Modifying Data Models
1. ✅ Read DATA_MODEL.md
2. ✅ Inspect Room entities
3. ✅ Check DAO implementations
4. ✅ Document changes

### Before Changing Architecture
1. ✅ Read ANDROID_ARCHITECTURE.md
2. ✅ Check existing patterns
3. ✅ Understand why current approach exists
4. ✅ Document rationale for change

---

## Rule 3: No Duplicate Implementation

**Reuse before creating new.**

### Check Before Building
- Is there a similar component?
- Can existing component be extended?
- Can existing screen be adapted?
- Can existing utility function be reused?

### Pattern: Composition Over Duplication

```kotlin
// ❌ Bad: Duplicate code
@Composable
fun VehicleCardType1() { /* ... */ }

@Composable
fun VehicleCardType2() { /* ... */ }

// ✅ Good: Compose existing component
@Composable
fun VehicleCard(variant: CardVariant) { /* ... */ }

@Composable
fun VehicleCardType1() {
    VehicleCard(variant = CardVariant.Standard)
}

@Composable
fun VehicleCardType2() {
    VehicleCard(variant = CardVariant.Compact)
}
```

---

## Rule 4: Follow Existing Design System

**Use established colors, typography, spacing, components.**

### Before Creating New Component
1. ✅ Check DESIGN_SYSTEM.md
2. ✅ Check Color.kt, Typography.kt, Spacing.kt
3. ✅ Use existing design tokens
4. ✅ Follow component patterns

### Before Using Custom Colors/Fonts
1. ✅ Are design tokens available?
2. ✅ If not, add to design system
3. ✅ Update DESIGN_SYSTEM.md
4. ✅ Commit changes

---

## Rule 5: Follow Existing Architecture

**Maintain MVVM + Clean Architecture pattern.**

### Adding a New Feature
1. ✅ Create domain entity
2. ✅ Create repository interface
3. ✅ Create repository implementation
4. ✅ Create use cases (if needed)
5. ✅ Create ViewModel
6. ✅ Create Composable screens
7. ✅ Add navigation routes
8. ✅ Connect to bottom nav (if applicable)

### Don't:
- ❌ Put business logic in Composables
- ❌ Directly access database in UI
- ❌ Skip repository pattern
- ❌ Create monolithic screens

---

## Rule 6: Document Architectural Changes

**If changing how something works, document WHY.**

### Before Major Change
1. ✅ Identify what's changing
2. ✅ Identify why change is needed
3. ✅ Identify what files are affected
4. ✅ Document in DEVELOPMENT_STATUS.md

### Architectural Change Template

```markdown
## Change: [What Changed]

**Date**: [Date]
**Affected Files**: [List of files]
**Reason**: [Why this change was necessary]
**Before**: [Old approach]
**After**: [New approach]
**Impact**: [What else is affected]
```

---

## Rule 7: Justify Dependencies

**Before adding any library, justify why.**

### Before Adding Dependency
1. ✅ Check if already exists
2. ✅ Check if Android built-in can do it
3. ✅ Document rationale in commit
4. ✅ Update build.gradle with version pinning

### Dependencies Already Approved (V1)
- Jetpack Compose
- Hilt
- Room
- Coroutines
- Flow
- Navigation Compose
- Coil (images, if added)

### Dependencies That Need Approval
- Any networking library (Retrofit already planned)
- Any ML/AI library
- Heavyweight libraries
- Security/crypto libraries

---

## Rule 8: Keep Changes Incremental

**Small, focused changes are easier to understand and maintain.**

### Good Commit Pattern
```
1 commit = 1 logical change
- Add single screen
- Fix one bug
- Add one component
- Update one data model section
```

### Bad Commit Pattern
```
❌ "Add everything"
❌ "WIP, rough version"
❌ "Refactored multiple modules"
❌ Mixed unrelated changes
```

---

## Rule 9: Update Documentation When Decisions Change

**If requirements/design/architecture changes, update docs.**

### After Major Implementation Work
1. ✅ Update DEVELOPMENT_STATUS.md
2. ✅ Update IMPLEMENTATION_PLAN.md if timeline changed
3. ✅ Update DESIGN_SYSTEM.md if new components
4. ✅ Update DATA_MODEL.md if schema changed
5. ✅ Update NAVIGATION_MAP.md if flows changed

### Example: New Screen Added

```markdown
# DEVELOPMENT_STATUS.md Update

## Phase 2 - Explore Features
- ✅ Search Screen (completed)
- ✅ Filter Screen (completed)
- 🔄 Results List (in progress - NEW)
- ⏳ Sort Options

## Recent Changes
- Added ResultsListScreen with pagination
- Added SortOptionsScreen for sort UI
- Updated navigation to include new screens
```

---

## Rule 10: Ask When Unclear

**Don't guess about ambiguous requirements.**

### When to Ask
- ❌ Unclear product requirement
- ❌ Conflicting requirements
- ❌ Design vs. implementation conflict
- ❌ Performance vs. feature tradeoff
- ❌ Security implications
- ❌ Major architectural decision

### When to Decide Independently
- ✅ Variable naming (follow conventions)
- ✅ File organization (follow structure)
- ✅ Component composition (follow patterns)
- ✅ Error handling (follow established patterns)
- ✅ Minor UI tweaks (follow design system)

### How to Ask
```
ASSUMPTION NEEDED:
[Briefly describe the ambiguity]

OPTIONS:
1. [Option A] - [Tradeoff]
2. [Option B] - [Tradeoff]

RECOMMENDATION: [Which option, why]

Can you confirm preferred approach?
```

---

## Rule 11: No Silent Architectural Changes

**If changing how something fundamental works, make it visible.**

### Examples of Major Changes
- Changing state management approach
- Changing navigation structure
- Changing data layer
- Changing DI setup
- Removing/replacing component pattern

### For Major Changes:
1. ✅ Create a document explaining change
2. ✅ Commit with detailed message
3. ✅ Update DEVELOPMENT_STATUS.md
4. ✅ Update affected documentation

---

## Rule 12: Git-Friendly Development

**Project should be reproducible and portable.**

### Commit Standards

**Format**: `<type>: <subject>`

```
feat: Add vehicle detail screen
fix: Resolve crash in favorites tab
docs: Update navigation documentation
refactor: Simplify vehicle repository
test: Add unit tests for ListViewModel
style: Apply formatting to components
chore: Update dependencies
```

### Commit Rules
- ✅ Meaningful, descriptive messages
- ✅ One logical change per commit
- ✅ No unrelated modifications
- ✅ No generated files
- ✅ No secrets or tokens

### No Committed:
- ❌ API keys or tokens
- ❌ Generated build files (except necessary config)
- ❌ IDE-specific files (except .idea/vcs.xml)
- ❌ Temporary files
- ❌ Sensitive data

---

## Rule 13: Code Review Mentality

**Write code as if someone else will review it.**

### Before Committing
- ✅ Code follows existing patterns
- ✅ No hardcoded strings (use strings.xml)
- ✅ Proper null safety
- ✅ Error cases handled
- ✅ Logical variable names
- ✅ Comments for complex logic
- ✅ No dead code
- ✅ Tests included (where applicable)

### Example: Good Code

```kotlin
@HiltViewModel
class VehicleDetailViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    private val favoriteRepository: FavoriteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val vehicleId: String = savedStateHandle.get("vehicleId") ?: ""
    
    private val _uiState = MutableStateFlow<VehicleDetailState>(
        VehicleDetailState.Loading
    )
    val uiState: StateFlow<VehicleDetailState> = _uiState.asStateFlow()
    
    init {
        loadVehicleDetail()
    }
    
    private fun loadVehicleDetail() {
        viewModelScope.launch {
            try {
                val vehicle = vehicleRepository.getVehicle(vehicleId)
                val isFavorite = favoriteRepository.isFavorited(vehicleId)
                _uiState.value = VehicleDetailState.Success(vehicle, isFavorite)
            } catch (e: Exception) {
                _uiState.value = VehicleDetailState.Error(e.message ?: "Unknown error")
            }
        }
    }
    
    fun toggleFavorite() {
        viewModelScope.launch {
            favoriteRepository.toggleFavorite(vehicleId)
        }
    }
}
```

---

## Rule 14: Handoff-Ready Documentation

**Every new AI agent should understand the project in 30 minutes.**

### Documentation Checklist
- ✅ PRODUCT_REQUIREMENTS.md reads like spec
- ✅ ANDROID_ARCHITECTURE.md explains structure
- ✅ DEVELOPMENT_STATUS.md shows current progress
- ✅ IMPLEMENTATION_PLAN.md shows next steps
- ✅ Code has meaningful comments
- ✅ No critical decisions only in memory

### Handoff Checklist
When switching AI agents:
1. ✅ New agent reads all MD files
2. ✅ New agent checks DEVELOPMENT_STATUS.md
3. ✅ New agent reviews recent commits
4. ✅ New agent asks clarification questions if needed
5. ✅ New agent starts from IMPLEMENTATION_PLAN.md

---

## Rule 15: Project Configuration

**Setup enables any developer/AI to get started.**

### .gitignore
- ✅ No IDE-specific (except .idea/vcs.xml)
- ✅ No build artifacts
- ✅ No dependencies
- ✅ No secrets

### build.gradle
- ✅ Clear dependency versions
- ✅ Comments on non-obvious dependencies
- ✅ SDK versions documented

### Code Structure
- ✅ Logical module organization
- ✅ Clear naming conventions
- ✅ Consistent patterns throughout

---

## Quick Reference for New AI Agent

**Getting started checklist:**

```
1. Read PRODUCT_REQUIREMENTS.md
2. Read ANDROID_ARCHITECTURE.md
3. Read DEVELOPMENT_STATUS.md
4. Check IMPLEMENTATION_PLAN.md for next task
5. Read recent Git commits
6. Run the project
7. Inspect code structure
8. Review COMPONENT_INVENTORY.md if adding UI
9. Ask clarification if needed
```

---

## Summary

**These rules ensure:**
- ✅ Project continuity across AI agents
- ✅ Clean, maintainable codebase
- ✅ Clear decision documentation
- ✅ Easy onboarding for new developers
- ✅ No hidden context or assumptions
- ✅ Professional production-quality code

**The goal**: Repository + documentation should be sufficient for ANY competent developer (human or AI) to understand and continue the project.

---

**END OF AI AGENT RULES**

*Last Updated: August 10, 2026*  
*Version: 1.0.0*
