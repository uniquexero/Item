package xero.plugin.item;

import org.bukkit.plugin.java.JavaPlugin;

import xero.command.bukkit.CommandComponent;
import xero.command.bukkit.CommandManager;
import xero.plugin.item.command.CommandItemAmount;
import xero.plugin.item.command.CommandItemArmorColor;
import xero.plugin.item.command.CommandItemAuthor;
import xero.plugin.item.command.CommandItemData;
import xero.plugin.item.command.CommandItemEnchant;
import xero.plugin.item.command.CommandItemId;
import xero.plugin.item.command.CommandItemLore;
import xero.plugin.item.command.CommandItemName;
import xero.plugin.item.command.CommandItemPotion;
import xero.plugin.item.command.CommandItemSkullOwner;
import xero.plugin.item.command.CommandItemStoredEnchant;
import xero.plugin.item.command.CommandItemTitle;
import xero.plugin.item.command.CommandItemUnbreakable;
import xero.plugin.item.command.CommandItemUnseal;

public class ItemPlugin extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		setupCommand();
	}

	private void setupCommand()
	{
		CommandComponent id, amount, data, name, lore, color, title, author, unseal, skullOwner, enchant, storedEnchant, potion, unbreakable;

		id = new CommandItemId();
		amount = new CommandItemAmount();
		data = new CommandItemData();
		name = new CommandItemName();
		lore = new CommandItemLore("add", "remove", "set");
		color = new CommandItemArmorColor();
		title = new CommandItemTitle();
		author = new CommandItemAuthor();
		unseal = new CommandItemUnseal();
		skullOwner = new CommandItemSkullOwner();
		enchant = new CommandItemEnchant();
		storedEnchant = new CommandItemStoredEnchant();
		potion = new CommandItemPotion();
		unbreakable = new CommandItemUnbreakable();

		new CommandManager().addHelp("help").addComponent("id", id).addComponent("amount", amount).addComponent("data", data).addComponent("name", name)
		            .addComponent("lore", lore).addComponent("color", color).addComponent("title", title).addComponent("author", author)
		            .addComponent("unseal", unseal).addComponent("skullOwner", skullOwner).addComponent("enchant", enchant)
		            .addComponent("storedEnchant", storedEnchant).addComponent("potion", potion).addComponent("unbreakable", unbreakable)
		            .register(getCommand("item"));

		lore = new CommandItemLore("추가", "제거", "설정");

		new CommandManager().addHelp("도움말").addComponent("타입", id).addComponent("수량", amount).addComponent("데이터", data).addComponent("이름", name)
		            .addComponent("설명", lore).addComponent("색상", color).addComponent("제목", title).addComponent("작성자", author)
		            .addComponent("해제", unseal).addComponent("머리", skullOwner).addComponent("인챈트", enchant)
		            .addComponent("인챈트북", storedEnchant).addComponent("포션", potion).addComponent("내구성", unbreakable)
		            .register(getCommand("아이템"));
	}
}
