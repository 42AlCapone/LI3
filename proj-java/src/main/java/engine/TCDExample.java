package engine;

import common.MyLog;
import common.Pair;
import li3.TADCommunity;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.lang.System;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.Comparator;



public class TCDExample implements TADCommunity {

    //private MyLog qelog;

    private CatUsers users;
    private CatPerguntas perguntas;
    private CatRespostas respostas;

    
    public TCDExample() {
        this.users = new CatUsers();
        this.perguntas = new CatPerguntas();
        this.respostas = new CatRespostas();
    }

    public void load(String dumpPath) {
        

        
        Parser.parseUsers(dumpPath,this.users);
        Parser.parsePosts(dumpPath,this.users, this.perguntas,this.respostas);
        
       

    }
/*
    // Query 1
    public Pair<String,String> infoFromPost(long id) {
        String title, name;
        title=name=null;
        

        Pair <String,String> pair = new Pair(null,null);
        Long objLong = new Long(id);
        Pergunta p1 = null;
        Pergunta p2 = null;
        Resposta r = null;
        User u = null;
        p1 = this.perguntas.getPergunta(objLong);
        
            //System.out.println(title);
        r = this.respostas.getResp(objLong);
        
        if(p1!=null){
            u = this.users.getUser(p1.getOwnerIDp());
            title = p1.getTitle();
            name = u.getName();
            pair.setFst(title);
            pair.setSecond(name);
        }

        else if (r!=null){
            
            
            p2 = this.perguntas.getPergunta(r.getParentID());  
            u = this.users.getUser(r.getOwnerIDr());
            title = p2.getTitle();
            name = u.getName();
            pair.setFst(title);
            pair.setSecond(name);
            

        }
        return pair;
        //return pair;
    }
    */

    // Query 2
    public List<Long> topMostActive(int N) {
        Comparator<Map.Entry<Long,User>> comparador = new ComparadorNrPosts();
        List<Long> list = new ArrayList<Long>();
        list = this.users.getCatUsers().entrySet().stream().sorted(comparador)
        .limit(N)
        .map(e->e.getValue().getUserID())
        .collect(Collectors.toCollection(ArrayList :: new));
        return list;
    }
/*
    // Query 3
    public Pair<Long,Long> totalPosts(LocalDate begin, LocalDate end) {
        return new Pair<>(3667L,4102L);
    }

    // Query 4
    public List<Long> questionsWithTag(String tag, LocalDate begin, LocalDate end) {
        return Arrays.asList(276174L,276029L,274462L,274324L,274316L,274141L,274100L,272937L,
                272813L,272754L,272666L,272565L,272450L,272313L,271816L,271683L,271647L,270853L,270608L,270528L,270488L,
                270188L,270014L,269876L,269781L,269095L,268501L,268155L,267746L,267656L,267625L,266742L,266335L,266016L,
                265531L,265483L,265443L,265347L,265104L,265067L,265028L,264764L,264762L,264616L,264525L,264292L,263816L,
                263740L,263460L,263405L,263378L,263253L,262733L,262574L);
    }

    // Query 5
    public Pair<String, List<Long>> getUserInfo(long id) {
        String shortBio = "<p>Coder. JS, Perl, Python, Basic<br>Books/movies: SF+F.<br>Dead:" +
                "dell 9300<br>Dead: dell 1720 as of may 10th 2011.</p>\n" +
                "<p>Current system: Acer Aspire 7750G.<br>\n" +
                "Works OOTB as of Ubuntu 12.04.<br></p>";
        List<Long> ids = Arrays.asList(982507L,982455L,980877L,980197L,980189L,976713L,974412L,
                974359L,973895L,973838L);
        return new Pair<>(shortBio,ids);
    }

    // Query 6
    public List<Long> mostVotedAnswers(int N, LocalDate begin, LocalDate end) {
        return Arrays.asList(701775L,697197L,694560L,696641L,704208L);
    }

    // Query 7
    public List<Long> mostAnsweredQuestions(int N, LocalDate begin, LocalDate end) {
        return Arrays.asList(505506L,508221L,506510L,508029L,506824L,505581L,505368L,509498L,509283L,508635L);
    }

    // Query 8
    public List<Long> containsWord(int N, String word) {
        return Arrays.asList(980835L,979082L,974117L,974105L,973832L,971812L,971056L,968451L,964999L,962770L);
    }

    // Query 9
    public List<Long> bothParticipated(int N, long id1, long id2) {
        return Arrays.asList(594L);
    }
*/
    // Query 10
    public long betterAnswer(long id) {
        List<Long> listResp = perguntas.getPergunta(id).getRespostas();
        double best,aux;
        best=aux=0;
        long bestID = -1;
        for(Long resp : listResp){
            aux = respostas.getResp(resp).getRate();
            if (aux>best){
            best = aux;
            bestID = respostas.getResp(resp).getRespID();
            }
        }    
        return bestID;
    }

/*
    // Query 11
    public List<Long> mostUsedBestRep(int N, LocalDate begin, LocalDate end) {
        return Arrays.asList(6L,29L,72L,163L,587L);
    }
    */

    public void clear(){
        this.users.clearCatUsers();
        this.perguntas.clearCatPerg();
        this.respostas.clearCatResp();
        return;
    }
}
