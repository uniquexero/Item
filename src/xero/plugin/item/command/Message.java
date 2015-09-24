package xero.plugin.item.command;

import xero.message.MessageSource;

public final class Message
{
	public static final MessageSource NOT_FOUND_ITEM = new MessageSource("아이템 §c<item>(을)를 찾지 못했습니다.");
	public static final MessageSource OUT_OF_INDEX = new MessageSource("범위를 벗어났습니다. §c<index>");
	public static final MessageSource NOT_FOUND_ENCHANTMENT = new MessageSource("인챈트 §c<ench>§r(을)를 찾지 못했습니다.");
	public static final MessageSource NOT_FOUND_POTION_TYPE = new MessageSource("포션 효과 §c<potion>§r(을)를 찾지 못했습니다.");
	public static final MessageSource INVALID_COLOR = new MessageSource("잘못된 색상코드입니다. 예) DDFF11");
	
	private Message() {}
}
