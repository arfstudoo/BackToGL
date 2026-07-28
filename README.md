BackToGL is a Fabric mod for Minecraft 26.2 that forces the game to use the classic OpenGL 3.3 graphics engine instead of Vulkan.

In Minecraft 26.2, Vulkan became the default graphics backend. On older graphics cards with incomplete Vulkan drivers, this causes invisible water, missing translucent blocks, black textures, or instant crashes on startup. 

BackToGL forces OpenGL 3.3 Core Profile before window initialization and applies hardware buffer fixes.

### Key Features

- Forces OpenGL 3.3: Disables the experimental Vulkan renderer introduced in 26.2.
- Fixes Invisible Water & Glass: Restores correct depth and blending states for translucent blocks.
- Prevents Crashes on Older GPUs: Solves driver-level buffer mapping errors on NVIDIA GT series, Intel HD Graphics, and AMD Radeon HD cards.
- Locks Graphics API in Settings: Disables the API button in video settings so you cannot accidentally switch back to Vulkan.
- Auto Config Fix: Automatically repairs options.txt if the game was saved with Vulkan enabled.
- F3 Overlay Tag: Displays an indicator on the F3 screen confirming OpenGL is active.