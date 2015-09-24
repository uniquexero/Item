package xero.plugin.item.command;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import xero.command.ArgumentList;
import xero.command.bukkit.PlayerCommandComponent;
import xero.minecraft.inventory.XeroPlayerInventory;
import xero.minecraft.item.XeroItemStack;

public abstract class ItemCommand extends PlayerCommandComponent
{
	public ItemCommand(String usage, String description, String permission, int argumentLength)
	{
		super(usage, description, permission, argumentLength);
	}

	@Override
	public final boolean onCommand(Player sender, Command command, String label, String componentLabel, ArgumentList args)
	{
		XeroItemStack item = XeroPlayerInventory.fromInventory(sender.getInventory()).getItemInHand();
		
		if (item == null)
			return false;
		
		return onCommand(sender, command, label, componentLabel, args, item);
	}
	
	public abstract boolean onCommand(Player sender, Command command, String label, String componentLabel, ArgumentList args, XeroItemStack item);
}
