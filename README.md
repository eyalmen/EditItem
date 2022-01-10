# EditItem
Edit every single aspect of an item with one command!
Features:

/edititem
Use this command to edit the item in your hand/

/eihelp
Use this to understand every subcommand of the /edititem command.

EditItem Subcommands:

name:
This sets the name of the item in your hand to whatever you type
You can use the bukkit color codes if you want to by using '&' followed by a valid color code/.

unbreakable:
This allows you to set whether the item in your hand is unbreakable or not.
Use true to enable it and false to disable it.

enchantmentGlint:
This makes your item have the enchantment glint while not showing an enchantment in the lore.
Your item can't be enchanted if you want to add this effect because all it does is add a useless enchantment to the item and hide the enchant in the lore.
Use true to enable the effect and false to disable it.

addEnchant:
This adds the enchantment you have selected to the item.
If you don't set a level for your enchantment it will be 1 by default.
You can set the level to any number regardless of the maximum enchant level.
You can't have enchantments and the enchantment glint enabled at the same time.
You can however hide the enchantments using the hideInDescription feature.

removeEnchant:
Removes the specified enchantment from the item.

hideInDescription:
This allows you to prevent a specific part of the item lore from showing.
The names are self explanatory.

addLore:
Adds a line of lore to the item.
You can use the bukkit color codes by using the '&' character and a valid code.

removeLore:
Removes the lore at a specified line. Doesn't work if there is no line at that number or if you didn't input a number.

armorColor:
Changes the color of a leather item (leather armor, leather horse armor).
Only works if you are holding leather armor.");
You can either use a predetermined color (red, blue, etc) or a hexadecimal code.

skin:
Sets the skin of a player head. Only works if you are holding a player head.
You can either use a player's name for the skin or you can use a link from textures.minecraft.net
