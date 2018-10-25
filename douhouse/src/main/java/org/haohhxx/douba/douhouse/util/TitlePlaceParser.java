package org.haohhxx.douba.douhouse.util;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhenyuan_hao@163.com
 */
public class TitlePlaceParser {

    private static Segment segment = HanLP.newSegment().enablePlaceRecognize(true);

    /**
     * @param tofind tofindStr
     * @param all textAll
     * @return
     */
    public static String findStr(String tofind, String all) {
        String str = "";
        Pattern p = Pattern.compile(tofind);
        Matcher m = p.matcher(all);
        while (m.find()) {
            str = m.group();
        }
        return str;
    }

    public static String find(String text){
        if("".equals(text)){
            return "";
        }
        String maxLenRe = "";
        int max = 0;

        String regix = "%s.{0,10}[村,厦,路,旁,站,寓,区]";
        List<Term> cuts = segment.seg(text);
        for(Term term:cuts){
            if(term.nature.startsWith("ns")||
                    term.nature.startsWith("f")||
                    term.nature.startsWith("nr")||
                    term.nature.startsWith("nz")){
                String tofind = String.format(regix,term.word);
                String found = findStr(tofind,text);
                if(!"".equals(found)){
                    int foundLen = found.length();
                    if(foundLen>max){
                        max = foundLen;
                        maxLenRe = found;
                    }
                }
            }
        }
        maxLenRe = format(maxLenRe);
        return maxLenRe;
    }
    private static String format(String maxLenRe){
        return maxLenRe.replaceAll("高档小区","");
    }

}
