package autolab.censorialchat.filters;

import com.vdurmont.emoji.EmojiParser;

public class EmojiFilter {
    public static String replaceEmoji(String str) {
        return EmojiParser.parseToUnicode(str);
    }
}
