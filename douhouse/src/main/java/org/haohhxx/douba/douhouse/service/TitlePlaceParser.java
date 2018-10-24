package org.haohhxx.douba.douhouse.service;

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

    private Segment segment = HanLP.newSegment().enablePlaceRecognize(true);

    /**
     * @param tofind tofindStr
     * @param all textAll
     * @return
     */
    private static String findStr(String tofind, String all) {
        String str = null;
        Pattern p = Pattern.compile(tofind);
        Matcher m = p.matcher(all);
        while (m.find()) {
            str = m.group();
        }
        return str;
    }

    public void find(String text){
        String regix = "%s.{0,10}[村,厦,路,旁,站,寓,区]";
        System.out.println("text:");
        List<Term> cuts = segment.seg(text);
        System.out.println(cuts);
        for(Term term:cuts){
            if(term.nature.startsWith("ns")||
                    term.nature.startsWith("f")||
                    term.nature.startsWith("nr")||
                    term.nature.startsWith("nz")){
                String tofind = String.format(regix,term.word);
                String found = findStr(tofind,text);
                System.out.println(tofind);
                System.out.println(found);
            }
        }
    }
}
