package com.angorithm;

/**
 * @author lxq
 * @date 2021年07月08日 11:43
 */
public class 反转字符串 {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        String s1 = solve("ashdjfhakljfhgkjsbdfglelrkjghwkerbgnmbdgbsdfgsm,dbfgklsdflkgjhsdflbgs,mdbfg,msdbfgl" +
                "sdfkljghlskjdhfgkljhsdfjklgsmndbfv,mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb,mdsbfa" +
                "bdskjfhbabfgnmsbdfgblksjdhfgjsdfgsdfgsdfgadgmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb");
        long l1 = System.currentTimeMillis();

        String s2 = solve1("ashdjfhakljfhgkjsbdfglelrkjghwkerbgnmbdgbsdfgsm,dbfgklsdflkgjhsdflbgs,mdbfg,msdbfgl" +
                "sdfkljghlskjdhfgkljhsdfjklgsmndbfv,mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb,mdsbfa" +
                "bdskjfhbabfgnmsbdfgblksjdhfgjsdfgsdfgsdfgadgmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb" +
                "mbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnbmbcbvlkajdlkjahsdlkjhfakjsdfhasdjfklhlasdjkfljkasdbfmnb");
        long l2 = System.currentTimeMillis();

        System.out.println(l);
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l1 - l);
        System.out.println(l2 - l1);
    }

    /**
     * 写出一个程序，接受一个字符串，然后输出该字符串反转后的字符串。（字符串长度不超过1000）
     * "abcdc" -> "cdcba"
     * @author lxq
     * @date 2021/7/8 11:43
     * @param str
     * @return String
     */
    public static String solve (String str) {
        char[] chars = str.toCharArray();
        int font = 0;
        int end = chars.length - 1;

        while (font < end){
            chars[font] ^= chars[end];
            chars[end] ^= chars[font];
            chars[font++] ^= chars[end--];
        }
        return new String(chars);
    }

    public static String solve1 (String str) {
        char[] chars = str.toCharArray();
        int font = 0;
        int end = chars.length - 1;
        char tmp;

        while (font < end){
            tmp = chars[font];
            chars[font++] = chars[end];
            chars[end--] = tmp;
        }
        return new String(chars);
    }
}
