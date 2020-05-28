package autolab.censorialchat.filters;

import com.vdurmont.emoji.EmojiParser;

public class EmojiFilter implements IFilter {
    public static String replaceEmoji(String str) {
        return EmojiParser.parseToUnicode(str);
    }

    @Override
    public String filter(String string) {
        return replaceEmoji(string);
    }
}
