package xero.plugin.item.command;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import xero.command.ArgumentList;
import xero.minecraft.item.XeroItem;
import xero.minecraft.item.XeroItemStack;
import xero.plugin.item.Permissions;

public class CommandItemId extends ItemCommand
{
	public CommandItemId()
	{
		super("<id>", "타입을 변경합니다.", Permissions.ID, 1);
	}

	@Override
	public boolean onCommand(Player sender, Command command, String label, String componentLabel, ArgumentList args, XeroItemStack itemStack)
	{
		String name = args.next();
		XeroItem item = XeroItem.getItem(name);

		if (item == null)
		{
			try
			{
				item = XeroItem.getItem(Integer.parseInt(name));
			}
			catch (NumberFormatException e)
			{}
			
			if (item == null)
			{
				sender.sendMessage(Message.NOT_FOUND_ITEM.toString(name));
				return true;
			}
		}
		
		itemStack.setItem(item);
		broadcast(sender, label, componentLabel, name);
		return true;
	}
}
