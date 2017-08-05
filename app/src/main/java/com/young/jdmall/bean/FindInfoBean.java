package com.young.jdmall.bean;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 钟志鹏 on 2017/8/2.
 */

public class FindInfoBean {
    public int id;
    public int type;
    public String title;
    public String Icon;
    public String name;
    public long visitsSum;
    public String location;

    public String image1;
    public String image2;
    public String image3;

    public String detail;

    public static String[] sStrings = new String[]{"不一样的机械师，体验不一样的机械键盘",};

    public FindInfoBean(int id, int type, String title, String icon, String name, long visitsSum, String location, String image1, String image2, String image3, String detail) {
        this.id = id;
        this.type = type;
        this.title = title;
        Icon = icon;
        this.name = name;
        this.visitsSum = visitsSum;
        this.location = location;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.detail = detail;

    }


//        stringBuilder.append("<text></text>");
//        stringBuilder.append("<image></image>");
//        stringBuilder.append("<goods><name></name><price></price><icon></icon><id></id></goods>");

    public static ArrayList<FindInfoBean> getFinInfoBeanList() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<container>");
        stringBuilder.append("<text> 每次讨论当下世界最高端的智能机品牌代表是谁时，身边的三星粉和苹果粉就会开始喋喋不休的争个没完。个人认为，三星手机使用的毕竟是安卓系统，论优化程度，安卓系统要比ios系统差很多，加上苹果科技感十足的大气外观，科研程度也领先世界一步，称其为高端智能机代表毫不过分。</text>");
        stringBuilder.append("<image>http://pic48.nipic.com/file/20140911/9885883_110743994000_2.jpg</image>");
        stringBuilder.append("<text>从苹果4时期，苹果公司就开创了属于自己的苹果时代。不过苹果公司采用的是撇脂营销策略，依靠其品牌知名度来大幅提高售价，很不亲民。</text>");
        stringBuilder.append("<image>http://pic61.nipic.com/file/20150314/9885883_180512532000_2.jpg</image>");
        stringBuilder.append("<text>不过，苹果手机还有一个特点，就是每逢新品上市，老版本就会自动降价来为新品让路，这一次的苹果6s plus也不例外。今年作为苹果手机上市的十周年，苹果8在还未上市时就曝光出了各式各样的新功能。</text>");
        stringBuilder.append("<image>http://img.jrjimg.cn/2017/04/20170418141233944.jpg</image>");
        stringBuilder.append("<text>甚至连实体照都被员工偷拍了出来。介于种种压力，以及苹果7因销量一般而不断降价的缘故，6s plus终于顶不住压力，来了一拨大跳水降价，现在的价格非常适合入手。</text>");
        stringBuilder.append("<image>http://www.ehedoors.com/UploadFiles/FCK/2015-08/201508258L0T6T4RHN.png</image>");
        stringBuilder.append("<goods><name>Apple iPhone 6s Plus(A1699) 32G 金色 移动联通电信4G手机</name><price>3999</price><icon>http://photocdn.sohu.com/20150425/Img411865458.jpg</icon><id>1</id></goods>");
        stringBuilder.append("<text>外观配色上，个人比较推荐金色版和玫瑰金版，看上去很大气，也很符合苹果一贯的高颜值路线。配置上，6S plus 采用了5.5英寸高清屏，前置500万+后置1200万像素摄像头，前置指纹识别，支持运动视频拍摄和慢动作视频拍摄模式，整体性能继承了苹果的优良传统，喜欢苹果的用户们要抓紧时间入手。</text>");
        stringBuilder.append("<image>https://img.alicdn.com/imgextra/i4/854234497/TB29LwLfXXXXXcfXpXXXXXXXXXX_!!854234497.jpg</image>");
        stringBuilder.append("<text>喜欢玩手游的用户，或者喜欢拿手机看电影的用户，最大的阻碍应该就是手机屏幕了，浩瀚的世界被禁锢在一个小小的方框里，确实让人很不爽。想突破屏幕尺寸的枷锁，这款苹果专用的手机投影仪一定能让你满意。</text>");
        stringBuilder.append("<image>http://www.cctime.com/UpLoadFile/2016/4/8/20164865292766.jpg</image>");
        stringBuilder.append("<goods><name>美高（MEGO）G6 苹果手机投影仪 高清家用投影机</name><price>1799</price><icon>http://img1.xungou.com/20160611/proimage/05/205122705_300_300.jpg</icon><id>2</id></goods>");
        stringBuilder.append("<text> 作为一款苹果专用的便携式手机投影仪，G6把高端和便利完美结合在了一起。不需要转换器，不需要APP，只需一根数据线，手机就可以和投影仪完美连接，非常方便。</text>");
        stringBuilder.append("<image>http://image.uczzd.cn/9521138383548034875.jpeg?id=0</image>");
        stringBuilder.append("<text>画面上，G6的画面比例为16:9，投影尺寸最高可达100寸，视觉效果非常震撼。最便利的一点是，G6不仅仅是手机投影仪，还能当充电宝使用，手机没电的时候，插上G6，边充电边拿大屏幕看电影，简直不要太爽！</text>");
        stringBuilder.append("<image>http://photocdn.sohu.com/20160315/Img440531508.jpg</image>");
        stringBuilder.append("<text>功能强大，G6的配置也很不错。G6搭载了全新的高透光镜头，镜头外部采用全新的镀膜设计，搭配超强的DLP成像技术，色彩还原度高，让画面也可以变得更加自然柔和，让我们清晰的看见画面中任何一个细节。</text>");
        stringBuilder.append("<image>http://photocdn.sohu.com/20160315/Img440531504.jpg</image>");
        stringBuilder.append("<text>看完这波降价，你是否开始心动了呢？喜欢苹果的用户千万不要错过这次降价，机不可失！</text>");
        stringBuilder.append("</container>");

        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("<container>");
        stringBuilder1.append("<text>大家最期待的人间四月天，已经历时大半个月了，很多mm迫不及待的翻出了自己的短裙，但是,20°的天气露出白花花的大腿似乎不太合适啊......瘦的姑娘傲娇的拿出肉色丝袜，美美的出街了，但是，习惯性穿黑色丝袜显瘦的妹子就懵了，到底是光腿还是继续穿丝袜？</text>");
        stringBuilder1.append("<image>http://www.iyi8.com/uploadfile/2015/0519/20150519111832279.jpg</image>");
        stringBuilder1.append("<text>其实这个问题很简单，腿粗的姑娘，为了显瘦，当然不要穿肉色的丝袜，可是黑色袜子跟浅色调的春夏衣裙也不搭调啊。其实，大家可以换个角度想，给自己一个月的时间瘦腿，先从半身裙穿起吧。</text>");
        stringBuilder1.append("<goods><name>新款毛衣</name><price>160</price><icon>http://114.115.218.128/market/images/product/detail/q20.jpg</icon><id>22</id></goods>");
        stringBuilder1.append("<text>牛仔比较休闲不挑人，比较日常，简约。雪纺偏淑女风，更挑人。选款时买的高腰的单品一般不会出错，还显瘦！上班族和学生党都可以穿。 </text>");
        stringBuilder1.append("<text>记得已经问过一个女生：天气这麼热，穿丝袜不是更热吗？答：不热啦，丝袜很薄。</text>");
        stringBuilder1.append("<text>高腰一步裙，视觉上显瘦效果超赞，微开叉，露出小性感，走路轻盈，搭配跟跟鞋，尽显淑女风范 </text>");
        stringBuilder1.append("<image>http://www.iyi8.com/uploadfile/2015/0419/20150419110731356.jpg</image>");
        stringBuilder1.append("<text>由于丝袜可以遮住腿毛，而夏天不穿丝袜就是光着腿，刮腿毛太费事，还不如穿一条丝袜复杂。</text>");
        stringBuilder1.append("<goods><name>女士外套</name><price>160</price><icon>http://114.115.218.128/market/images/product/detail/q10.jpg</icon><id>12</id></goods>");
        stringBuilder1.append("<text>防勾丝超薄款连体裤袜，非常的轻薄透气，很自然的一款肤色丝裤袜，超好的弹性，上身没有一丝的紧绷感，尽显腿部流畅优美的线条美。</text>");
        stringBuilder1.append("<text>薄薄的款式，现在这种天气穿刚刚好，，舒适透气，经典的抗菌裆设计，可以很好地保护，小腿看起来更纤细，没有那种束缚感穿上很舒服的。</text>");
        stringBuilder1.append("<image>http://www.iyi8.com/uploadfile/2015/0531/20150531100425209.jpg</image>");
        stringBuilder1.append("<text>这个理由满分，之前说丝袜很保暖，如今马上就现学现用了，不过这谁家公司也太任性了吧，空调开这麼低。</text>");
        stringBuilder1.append("<text>其实这才是姑娘们穿丝袜真实缘由，美观才是霸道，爲了美观，热不热冷不冷什麼的都曾经不重要了。</text>");
        stringBuilder1.append("<goods><name>韩版粉嫩外套</name><price>160</price><icon>http://114.115.218.128/market/images/product/detail/q13.jpg</icon><id>15</id></goods>");
        stringBuilder1.append("<text>校园情结满满的性感丝袜高通丝袜，上身轻巧美观更性感撩人，有着很好的上身体验，是你丝袜的最佳选择。</text>");
        stringBuilder1.append("<text>防勾丝，奢华蚕丝光泽袜口，袜口内镶嵌硅胶防滑层，超柔天鹅绒质感，欧洲大牌品质。杜绝由于身材偏瘦引起的下滑现象，适应身材更为广泛。 </text>");
        stringBuilder1.append("<image>http://www.iyi8.com/uploadfile/2014/1218/20141218105452144.jpg</image>");
        stringBuilder1.append("<text>夏天，就是露腿的季节。满大街的姑娘们，都穿上了漂亮的短裙，露出两条大长腿，时尚美丽不说，还显得个头很高。</text>");
        stringBuilder1.append("</container>");
//        stringBuilder.append("<image></image>");
//        stringBuilder.append("<goods><name></name><price></price><icon></icon><id></id></goods>");
        String text = stringBuilder.toString();
        ArrayList<FindInfoBean> findInfoBeen = new ArrayList<>();
        findInfoBeen.add(new FindInfoBean(0, 1, "价格跌入冰点，苹果6s plus是时候入手了", "http://a1239.phobos.apple.com/us/r30/Purple3/v4/13/44/98/134498ce-4c5d-d0e0-b85b-14038aad6dd7/pr_source.png", "叮咚日记", 234, "", "http://i03.pictn.sogoucdn.com/b7a058860e22ace8", "", "", stringBuilder.toString()));
        findInfoBeen.add(new FindInfoBean(0, 1, "穿裙子要穿丝袜么?怎么穿才不low", "http://cdn.duitang.com/uploads/item/201301/31/20130131025347_anUnd.thumb.600_0.jpeg", "美人鱼的眼泪", 21234, "", "http://panews.zjol.com.cn/pic/0/11/37/82/11378239_992527.jpg", "", "", stringBuilder1.toString()));
        for (int i = 1; i < 10; i++) {
            Random type = new Random();
            findInfoBeen.add(new FindInfoBean(i, 1, "不一样的机械师，体验不一样的机械键盘", "http://scimg.jb51.net/allimg/151026/14-151026145342264.jpg", "东东爱分享", 234, "", "http://dynamic-image.yesky.com/800x600/resources/product/20140722/80LB8WCS8KP3R42Q32MZ53782W56F0OE.jpg", "", "", ""));
            findInfoBeen.add(new FindInfoBean(i, 1, "究竟是啥玩意竟让展台ShowGirl无人关注？", "http://img.taopic.com/uploads/allimg/131218/234965-13121P3522917.jpg", "数字研习社", 8768, "", "http://image.club.china.com/twhb/25011632/2012/3/8/pictuku/1331173796228.jpg", "", "", ""));
            findInfoBeen.add(new FindInfoBean(i, 2, "原来这才是文胸的正确穿戴方式", "http://img0.ph.126.net/qXvd9RfE0KbrbWTsNRubIg==/6630742310256974469.jpg", "爱奇艺i红人圈", 99445, "", "http://www.iyi8.com/uploadfile/2016/0702/20160702120849224.jpg", "", "", ""));
            findInfoBeen.add(new FindInfoBean(i, 1, "超薄机械键盘真的会流行吗？", "http://www.people.com.cn/mediafile/pic/20150922/5/16487152744335524365.jpg", "神极营", 1323, "", "http://imgs.163xjk.cn/?url=inews.gtimg.com/newsapp_match/0/1380284044/0", "", "", ""));
            findInfoBeen.add(new FindInfoBean(i, 3, "锐龙游戏照样猛，暑假装机性价比高", "http://img.taopic.com/uploads/allimg/131218/234965-13121P3522917.jpg", "数字研习社", 5582, "", "http://oss.gkstk.com/images/2017/5/25182106975.jpg", "http://img5.pcpop.com/ArticleImages/730x547/4/4223/00422306678204592.jpg", "http://3c.3dmgame.com/uploadfile/2017/0306/20170306093927749.jpg", ""));

            findInfoBeen.add(new FindInfoBean(i, 1, "一颗子弹打穿4台iPhone，那么三星呢？", "http://i-2.shouji56.com/2015/4/15/9e064eb9-af23-4031-a15f-3e92c7b452ce.jpg", "路易蜀黍", 647, "", "http://img7.itiexue.net/2044/20449843.jpg", "", "", ""));
            findInfoBeen.add(new FindInfoBean(i, 3, "你说传感器，我说强。赛睿310系列游戏鼠标", "http://img1.cache.netease.com/catchpic/C/C6/C6FE00DA3C82B916F2EC5DD8DD76F170.png", "硬件学堂", 7468, "", "http://imga.deyi.com/forum/201311/20/121940fj5fxkkj5gqqvsvv.jpg", "http://imga.deyi.com/forum/201311/20/121941zpltyl6ti0fiup4i.jpg", "http://imga.deyi.com/forum/201311/20/121941f2ejb42wojzwtjjt.jpg", ""));
            findInfoBeen.add(new FindInfoBean(i, 1, "史上最恐怖的挖矿方式，单矿机12卡组装攻略", "http://n1.itc.cn/img8/wb/recom/2016/07/29/146978403059024937.JPEG", "你的益达", 467, "", "http://img5.pcpop.com/ArticleImages/0X0/3/3096/003096787.jpg", "", "", ""));
            findInfoBeen.add(new FindInfoBean(i, 1, "比300台iPhone8还要值钱的手机，中国仅有一台！", "http://pic39.nipic.com/20140311/2531170_170813449000_2.jpg", "科技小妖精", 4686, "", "http://file.muyee.com/images/upload/Image/201404221116.jpg", "", "", ""));
        }

        return findInfoBeen;
    }
}
