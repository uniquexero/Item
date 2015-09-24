package xero.plugin.item.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import xero.command.ArgumentList;
import xero.minecraft.item.XeroItemStack;
import xero.plugin.item.Permissions;

public class CommandItemName extends ItemCommand
{
	public CommandItemName()
	{
		super("<name...>", "이름을 변경합니다.", Permissions.NAME, 1);
	}
	
	@Override
	public boolean onCommand(Player sender, Command command, String label, String componentLabel, ArgumentList args, XeroItemStack item)
	{
		String name = ChatColor.translateAlternateColorCodes('&', args.join(' '));
		item.setDisplayName(name);
		broadcast(sender, label, componentLabel, name);
		return true;
	}
}
