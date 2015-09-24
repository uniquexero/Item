package xero.plugin.item.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import xero.command.ArgumentList;
import xero.minecraft.item.XeroItemStack;
import xero.plugin.item.Permissions;

public class CommandItemLore extends ItemCommand
{
	private final String add, remove, set;

	public CommandItemLore(String add, String remove, String set)
	{
		super(add + " | " + remove + " | " + set + " [line] [lore...]", "설명을 변경합니다.", Permissions.LORE, 1);

		this.add = add;
		this.remove = remove;
		this.set = set;
	}

	@Override
	public boolean onCommand(Player sender, Command command, String label, String componentLabel, ArgumentList args, XeroItemStack item)
	{
		List<String> lore = item.getLore();
		String c = args.next();
		int index = -1;
		StringBuilder builder = new StringBuilder();

		if (args.hasNext())
		{
			String s = args.next();

			try
			{
				index = Integer.parseInt(s);

				if (index < 0 || (lore != null && index >= lore.size()))
				{
					sender.sendMessage(Message.OUT_OF_INDEX.toString(Integer.toString(index)));
					return true;
				}
			}
			catch (NumberFormatException e)
			{
				builder.append(s);
			}
		}

		if (args.hasNext())
		{
			if (builder.length() > 0)
				builder.append(' ');

			while (true)
			{
				builder.append(args.next());

				if (args.hasNext())
				{
					builder.append(' ');
					continue;
				}

				break;
			}
		}

		String s = ChatColor.translateAlternateColorCodes('&', builder.toString());

		if (c.equals(this.add))
		{
			if (lore == null)
			{
				lore = new ArrayList<>();
			}

			if (index >= 0)
			{
				lore.add(index, s);
			}
			else
			{
				lore.add(s);
			}
		}
		else if (c.equals(this.remove))
		{
			if (lore != null)
			{
				if (index >= 0)
				{
					lore.remove(index);
				}
				else
				{
					lore.remove(s);
				}

				if (lore.isEmpty())
				{
					lore = null;
				}
			}
		}
		else if (c.equals(this.set))
		{
			if (index >= 0)
				lore.set(index, s);
			else
				return false;
		}
		else
			return false;

		item.setLore(lore);
		broadcast(sender, label, componentLabel, c + " " + s);

		return true;
	}

	@Override
	public List<String> onTabComplete(Player sender, Command command, String label, String componentLabel, ArgumentList args)
	{
		if (args.isLast())
		{
			String arg = args.next();
			int len = arg.length();
			ArrayList<String> complete = new ArrayList<>(3);
			
			if (this.add.regionMatches(true, 0, arg, 0, len))
				complete.add(this.add);
			if (this.remove.regionMatches(true, 0, arg, 0, len))
				complete.add(this.remove);
			if (this.set.regionMatches(true, 0, arg, 0, len))
				complete.add(this.set);
			
			return complete;
		}
		
		return Collections.emptyList();
	}

}
