package xero.plugin.item.command;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import xero.command.ArgumentList;
import xero.math.MathHelper;
import xero.minecraft.item.XeroItemStack;
import xero.plugin.item.Permissions;

public class CommandItemAmount extends ItemCommand
{
	public CommandItemAmount()
	{
		super("<amount>", "수량을 변경합니다.", Permissions.AMOUNT, 1);
	}
	
	@Override
	public boolean onCommand(Player sender, Command command, String label, String componentLabel, ArgumentList args, XeroItemStack item)
	{
		int amount = MathHelper.range(args.nextInt(), 1, item.getItem().getMaxStackSize());
		item.setAmount(amount);
		broadcast(sender, label, componentLabel, Integer.toString(amount));
		return true;
	}

}
