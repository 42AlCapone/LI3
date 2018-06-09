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
import java.util.TreeMap;
import java.util.Comparator;
import java.util.Collections;



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
        Long id1 = new Long(id);

        //Pergunta p = this.perguntas.getCatperg().getPergunta(id);
        Pergunta p;
            //System.out.println(title);
        //r = this.respostas.getResp(objLong);
        Resposta r;
        User u;
        

        if(this.perguntas.getCatPerg().containsKey(id1)){
            p = this.perguntas.getCatPerg().entrySet().stream().map(e->e.getValue())
            .filter(e->(e.getPergID()==id1)).ifPresent(s->s.get());

            u = this.users.getCatUsers().entrySet().stream().map(e->e.getValue())
            .filter(e->(e.getUserID()==p.getOwnerIDp())).ifPresent(s->s.get());

            title = p.getTitle();
            name = u.getName();
        }

        else if (this.respostas.getCatResp().containsKey(id1)){
            r = this.respostas.getCatResp().entrySet().stream().map(e->e.getValue())
            .filter(e->(e.getRespID()==id1)).ifPresent(s->s.get());
        
            p = this.perguntas.getCatPerg().entrySet().stream().map(e->e.getValue())
            .filter(e->(e.getPergID()==r.getParentID())).ifPresent(s->s.get());
            
            u = this.users.getCatUsers().entrySet().stream().map(e->e.getValue())
            .filter(e->(e.getUserID()==r.getOwnerIDr())).ifPresent(s->s.get());

            title = p.getTitle();
            name = u.getName();
        }

        return new Pair<>(title,name);
        //return pair;
    }
    */
    
    // Query 2
    public List<Long> topMostActive(int N) {
        Comparator<Map.Entry<Long,User>> comparador = new ComparadorNrPosts();
        List<Long> list = new ArrayList<Long>();
        list = this.users.getCatUsers().entrySet().stream()
        .sorted(comparador)
        .limit(N)
        .map(e->e.getValue().getUserID())
        .collect(Collectors.toCollection(ArrayList :: new));
        return list;
    }

    // Query 3
    public Pair<Long,Long> totalPosts(LocalDate begin, LocalDate end) {
        long perg, resp;
        perg=resp=0;
        for (Pergunta p : this.perguntas.getCatPerg().values()){
            if(p.getPergDate().compareTo(begin)>=0 && p.getPergDate().compareTo(end)<=0)
                perg++;
        }
        
        for (Resposta r : this.respostas.getCatResp().values()){
            if(r.getRespDate().compareTo(begin)>=0 && r.getRespDate().compareTo(end)<=0)
                resp++;
        }
        return new Pair<>(perg,resp);
    }

    // Query 4
    public List<Long> questionsWithTag(String tag, LocalDate begin, LocalDate end) {
        Comparator<Map.Entry<Long,Pergunta>> comparador = new ComparadorDates();
        List<Long> list = new ArrayList<Long>();
        list = this.perguntas.getCatPerg().entrySet().stream()
        .filter(e->(e.getValue().getTags().contains(tag) && e.getValue().getPergDate().compareTo(begin)>0 && e.getValue().getPergDate().compareTo(end)<0))
        .sorted(comparador)
        .map(e->e.getValue().getPergID())
        .collect(Collectors.toCollection(ArrayList :: new));
        return list;
    }


    // Query 5
    public Pair<String, List<Long>> getUserInfo(long id) {
        String bio;
        Comparator<Map.Entry<Long,Pergunta>> comparadorP = new ComparadorDates();
        Comparator<Map.Entry<Long,Resposta>> comparadorR = new ComparadorDatesR();
        
        bio = this.users.getCatUsers().get(id).getBio();
        
        List<Pergunta> pergs = new ArrayList<Pergunta>();
        pergs = this.perguntas.getCatPerg().entrySet().stream()
        .filter(e->e.getValue().getOwnerIDp()==id)
        .sorted(comparadorP)
        .limit(10)
        .map(e->e.getValue())
        .collect(Collectors.toCollection(ArrayList :: new));

        List<Resposta> resps = new ArrayList<Resposta>();
        resps = this.respostas.getCatResp().entrySet().stream()
        .filter(e->e.getValue().getOwnerIDr()==id)
        .sorted(comparadorR)
        .limit(10)
        .map(e->e.getValue())
        .collect(Collectors.toCollection(ArrayList :: new));

        List<Long> top10 = new ArrayList<Long>();
        for (Pergunta p : pergs) {
            for (Resposta r : resps) {
                if(p.getPergDate().compareTo(r.getRespDate())>0)
                    if(top10.contains(p.getPergID()))
                        break;
                    else    
                        top10.add(p.getPergID());
                else 
                    if(top10.contains(r.getRespID()))
                        break;
                    else
                        top10.add(r.getRespID());

            }
        }
        
        return new Pair<>(bio,top10);
    }

    // Query 6
    public List<Long> mostVotedAnswers(int N, LocalDate begin, LocalDate end) {
        Comparator<Map.Entry<Long,Resposta>> comparador = new ComparadorScore();
        List<Long> list = new ArrayList<Long>();
        list = this.respostas.getCatResp().entrySet().stream()
        .filter(e->(e.getValue().getRespDate().compareTo(begin)>0 && e.getValue().getRespDate().compareTo(end)<0))
        .sorted(comparador)
        .limit(N)
        .map(e->e.getValue().getRespID())
        .collect(Collectors.toCollection(ArrayList :: new));
        return list;
    }
/*
    // Query 7
    public List<Long> mostAnsweredQuestions(int N, LocalDate begin, LocalDate end) {
        Comparator<Map.Entry<Long,Pergunta>> comparador = new ComparadorNrResps();
        List<Pergunta> list = new ArrayList<Pergunta>();
        list = this.perguntas.getCatPerg().entrySet().stream()
        .filter(e->(e.getValue().getPergDate().compareTo(begin)>0 && e.getValue().getPergDate().compareTo(end)<0))
        .map(e->e.getValue())
        .collect(Collectors.toCollection(ArrayList :: new));

        Map<Integer,Pergunta> map = new TreeMap<Integer,Pergunta>(Collections.reverseOrder());
        int count;
        for (Pergunta p : list){
            
            count = 0;
            for(Resposta r : p.getRespostas()){
                if(r.getRespDate().compareTo(begin)>0 && r.getRespDate().compareTo(end)<0)
                    count++;
                
            } 
            map.put(count,p);
        }
        return map.entrySet().stream().limit(N).map(e->e.getValue().getPergID())
        .collect(Collectors.toCollection(ArrayList :: new));
        

        //return Arrays.asList(505506L,508221L,506510L,508029L,506824L,505581L,505368L,509498L,509283L,508635L);
        //[508221, 508029, 508635, 508278, 508546, 509174, 509193, 509176, 509197, 509191]
    }
    */

    // Query 8
    public List<Long> containsWord(int N, String word) {
        Comparator<Map.Entry<Long,Pergunta>> comparador = new ComparadorDates();
        List<Long> list = new ArrayList<Long>();
        list = this.perguntas.getCatPerg().entrySet().stream()
        .filter(e->(e.getValue().getTitle().contains(word)))
        .sorted(comparador)
        .limit(N)
        .map(e->e.getValue().getPergID())
        .collect(Collectors.toCollection(ArrayList :: new));
        return list;
    }

/*
    // Query 9
    public List<Long> bothParticipated(int N, long id1, long id2) {
        return Arrays.asList(594L);
    }
  */
   
    // Query 10
    public long betterAnswer(long id) {
        List<Resposta> list = perguntas.getPergunta(id).getRespostas();
        double best,aux;
        best=aux=0;
        long bestID = -1;
        for(Resposta resp : list){
            aux = resp.getRate();
            if (aux>best){
            best = aux;
            bestID = resp.getRespID();
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
