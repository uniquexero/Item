package xero.plugin.item.command;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import xero.command.ArgumentList;
import xero.minecraft.item.XeroItemStack;
import xero.minecraft.nbt.NBTCompound;
import xero.plugin.item.Permissions;

public class CommandItemArmorColor extends ItemCommand
{

	public CommandItemArmorColor()
	{
		super("<hex>", "가죽 갑옷의 색상을 설정합니다.", Permissions.COLOR, 1);
	}
	
	@Override
	public boolean onCommand(Player sender, Command command, String label, String componentLabel, ArgumentList args, XeroItemStack item)
	{
		int id = item.getId();
		
		if (id < 298 || id > 301)
			return false;
		
		String colorString = args.next();
		
		try
		{
			int color = Integer.decode("0x" + colorString);
			
			NBTCompound tag = item.getTag();
			
			if (tag == null)
				item.setTag(tag = NBTCompound.newInstance());
			
			NBTCompound display = tag.getCompound("display");
			
			if (display == null)
			{
				tag.setCompound("display", display = NBTCompound.newInstance());
			}
			
			display.setInt("color", color);
			broadcast(sender, label, componentLabel, Integer.toHexString(color).toUpperCase());
		}
		catch (NumberFormatException e)
		{
			sender.sendMessage(Message.INVALID_COLOR.toString(colorString));
		}
		
		return true;
	}
}
