package xero.plugin.item.command;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import xero.command.ArgumentList;
import xero.minecraft.item.XeroItemStack;
import xero.plugin.item.Permissions;

public class CommandItemData extends ItemCommand
{

	public CommandItemData()
	{
		super("<data>", "데이터를 변경합니다.", Permissions.DAMAGE, 1);
	}

	@Override
	public boolean onCommand(Player sender, Command command, String label, String componentLabel, ArgumentList args, XeroItemStack item)
	{
		int data = args.nextInt();
		item.setData(data);
		broadcast(sender, label, componentLabel, Integer.toString(data));
		return true;
	}

}
