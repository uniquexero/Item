package xero.plugin.item.command;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import xero.command.ArgumentList;
import xero.minecraft.item.XeroItemStack;
import xero.minecraft.nbt.NBTCompound;
import xero.plugin.item.Permissions;

public class CommandItemUnseal extends ItemCommand
{

	public CommandItemUnseal()
	{
		super(null, "쓰여진 책을 책과 깃펜으로 변경합니다.", Permissions.UNSEAL, 0);
	}

	@Override
	public boolean onCommand(Player sender, Command command, String label, String componentLabel, ArgumentList args, XeroItemStack item)
	{
		if (item.getId() != 387)
			return false;
		
		
		item.setId(386);
		
		NBTCompound tag = item.getTag();
		String title = tag == null ? null : tag.getString("title");
		
		broadcast(sender, label, componentLabel, title == null ? "§7null" : title);
		return true;
	}
}
