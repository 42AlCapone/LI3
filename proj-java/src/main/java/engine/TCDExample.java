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
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Comparator;
import java.util.Collections;



public class TCDExample implements TADCommunity {

    private CatUsers users;
    private CatPerguntas perguntas;
    private CatRespostas respostas;
    private CatTags tags;

    
    public TCDExample() {
        
        this.users = new CatUsers();
        this.perguntas = new CatPerguntas();
        this.respostas = new CatRespostas();
        this.tags = new CatTags();
    }

    public void load(String dumpPath) {
        
        Parser.parseUsers(dumpPath,this.users);
        Parser.parsePosts(dumpPath,this.users, this.perguntas,this.respostas);       
        Parser.parseTags(dumpPath,this.tags);
    }
  
    // Query 1
    public Pair<String,String> infoFromPost(long id) {

        String title, name;
        title=name=null;
        
        Pergunta p;
        Resposta r;
        User u;
        
        if(this.perguntas.getCatPerg().containsKey(id)){
            
            p = this.perguntas.getCatPerg().get(id);
            u = this.users.getCatUsers().get(p.getOwnerIDp());
            title = p.getTitle();
            name = u.getName();
        }

        else if (this.respostas.getCatResp().containsKey(id)){
  
            r = this.respostas.getCatResp().get(id);
            p = this.perguntas.getCatPerg().get(r.getParentID());
            u = this.users.getCatUsers().get(p.getOwnerIDp());
            title = p.getTitle();
            name = u.getName();
        }

        return new Pair<>(title,name);
    }
    
    
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
        
        for (Pergunta p : this.perguntas.getCatPerg().values())
            if(p.getPergDate().compareTo(begin)>=0 && p.getPergDate().compareTo(end)<=0)
                perg++;
        
        
        for (Resposta r : this.respostas.getCatResp().values())
            if(r.getRespDate().compareTo(begin)>=0 && r.getRespDate().compareTo(end)<=0)
                resp++;
        
        return new Pair<>(perg,resp);
    }

    // Query 4
    public List<Long> questionsWithTag(String tag, LocalDate begin, LocalDate end) {
        
        Comparator<Map.Entry<Long,Pergunta>> comparador = new ComparadorDatesP();
        List<Long> list = new ArrayList<Long>();
        list = this.perguntas.getCatPerg().entrySet().stream()
        .filter(e->(e.getValue().getTags().contains(tag) 
            && e.getValue().getPergDate().compareTo(begin)>=0 
            && e.getValue().getPergDate().compareTo(end)<=0))
        .sorted(comparador)
        .map(e->e.getValue().getPergID())
        .collect(Collectors.toCollection(ArrayList :: new));
        return list;
    }

    // Query 5
    public Pair<String, List<Long>> getUserInfo(long id) {
        
        Comparator<Map.Entry<Long,Pergunta>> comparadorP = new ComparadorDatesP();
        Comparator<Map.Entry<Long,Resposta>> comparadorR = new ComparadorDatesR();
        Comparator<Map.Entry<Long,LocalDate>> comparadorL = new ComparadorDatesL();
        
        String bio = this.users.getCatUsers().get(id).getBio();
             
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

        Map<Long,LocalDate> map = new HashMap<Long,LocalDate>();
        for (Pergunta p : pergs)
            map.put(p.getPergID(),p.getPergDate());
        for (Resposta r : resps)
            map.put(r.getRespID(),r.getRespDate());
        

        List<Long> top10 = new ArrayList<Long>();
        top10 = map.entrySet().stream().
        sorted(comparadorL)
        .limit(10)
        .map(e->e.getKey())
        .collect(Collectors.toCollection(ArrayList :: new));

        return new Pair<>(bio,top10);
    }



    // Query 6
    public List<Long> mostVotedAnswers(int N, LocalDate begin, LocalDate end) {
        
        Comparator<Map.Entry<Long,Resposta>> comparador = new ComparadorScore();
        List<Long> list = new ArrayList<Long>();
        list = this.respostas.getCatResp().entrySet().stream()
        .filter(e->(e.getValue().getRespDate().compareTo(begin)>=0 
            && e.getValue().getRespDate().compareTo(end)<=0))
        .sorted(comparador)
        .limit(N)
        .map(e->e.getValue().getRespID())
        .collect(Collectors.toCollection(ArrayList :: new));
        return list;
    }

    // Query 7
    public List<Long> mostAnsweredQuestions(int N, LocalDate begin, LocalDate end) {
        
        Comparator<Map.Entry<Long,Pergunta>> comparador = new ComparadorNrResps();
        List<Long> list = new ArrayList<Long>();
        list = this.perguntas.getCatPerg().entrySet().stream()
        .filter(e->(e.getValue().getPergDate().compareTo(begin)>=0 
            && e.getValue().getPergDate().compareTo(end)<=0))
        .sorted(comparador)
        .limit(N)
        .map(e->e.getValue().getPergID())
        .collect(Collectors.toCollection(ArrayList :: new));

/*
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
  */      
        return list;
    }
    

    // Query 8
    public List<Long> containsWord(int N, String word) {
        
        Comparator<Map.Entry<Long,Pergunta>> comparador = new ComparadorDatesP();
        List<Long> list = new ArrayList<Long>();
        list = this.perguntas.getCatPerg().entrySet().stream()
        .filter(e->(e.getValue().getTitle().contains(word)))
        .sorted(comparador)
        .limit(N)
        .map(e->e.getValue().getPergID())
        .collect(Collectors.toCollection(ArrayList :: new));
        return list;
    }


    // Query 9
    public List<Long> bothParticipated(int N, long id1, long id2) {
        

        //Perguntas user1
        List<Pergunta> pergsUser1 = this.perguntas.getCatPerg().entrySet().stream()
        .filter(e->e.getValue().getOwnerIDp()==id1)
        .map(e->e.getValue())
        .collect(Collectors.toCollection(ArrayList :: new));

        //Perguntas user2
        List<Pergunta> pergsUser2 = this.perguntas.getCatPerg().entrySet().stream()
        .filter(e->e.getValue().getOwnerIDp()==id2)
        .map(e->e.getValue())
        .collect(Collectors.toCollection(ArrayList :: new));

        Map<Long,Pergunta> map = new HashMap<Long,Pergunta>();
        Set<Resposta> set1;
        Set<Resposta> set2;

        //User1 na pergunta e user2 na resposta
        for(Pergunta p : pergsUser1){
            
            set1 = p.getRespostas();
            for(Resposta r : set1){

                if(r.getOwnerIDr()==id2){    
                    map.put(p.getPergID(),p);
                    break;  
                }
            }
        }

        //User2 na pergunta e user1 na resposta
        for(Pergunta p : pergsUser2){
            
            set2 = p.getRespostas();
            for(Resposta r : set2){

                if(r.getOwnerIDr()==id1){
                    map.put(p.getPergID(),p); 
                    break;
                }
            }
        }

        Set<Resposta> set3;
        int flag=0;
        
        //dois users em respostas
        for(Map.Entry<Long,Pergunta> entry : this.perguntas.getCatPerg().entrySet()){
            set3 = entry.getValue().getRespostas();
            flag=0;
            for(Resposta r : set3){
                if(flag==2) break;
                else if(flag==1)
                    if(r.getOwnerIDr()==id1 || r.getOwnerIDr()==id2){
                        flag++;
                        map.put(entry.getValue().getPergID(),entry.getValue()); 
                    }
                else if(r.getOwnerIDr()==id1 || r.getOwnerIDr()==id2) flag++;

                
            }

        }

        Comparator<Map.Entry<Long,Pergunta>> comparador = new ComparadorDatesP();
        List<Long> list = new ArrayList<Long>();
        list = map.entrySet().stream().sorted(comparador)
        .map(e->e.getValue().getPergID())
        .collect(Collectors.toCollection(ArrayList :: new));

        return list;
    }

   
    // Query 10
    public long betterAnswer(long id) {

        if(!perguntas.getCatPerg().containsKey(id)) return -1;
       
        Set<Resposta> set = this.perguntas.getCatPerg().get(id).getRespostas();
        long bestID = -1;
        double best,aux;
        best=aux=0;
        for(Resposta resp : set){
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
        
        Comparator<Map.Entry<Long,User>> comparador1 = new ComparadorRep();
        List<Long> listUsers = new ArrayList<Long>();
        listUsers = this.users.getCatUsers().entrySet().stream()
        .sorted(comparador1)
        .limit(N)
        .map(e->e.getKey())
        .collect(Collectors.toCollection(ArrayList :: new));
        List<Pergunta> listPerg = new ArrayList<Pergunta>();
        listPerg = this.perguntas.getCatPerg().entrySet().stream()
        .filter(e->e.getPergDate().compareTo(begin)>=0 && e.getPergDate().compareTo(end)<=0)
        .map(e->e.clone())
        .collect(Collectors.toCollection(ArrayList :: new));

        User user;
        for(Pergunta p : listPerg) {
            user = p.get
        }


        List<Pergunta> list;
        
        Map<String,Tag> best_tags = new HashMap<String,Tag>();
        for(Long u : listUsers) {
            
            list = new ArrayList<Pergunta>();
            int novo=0;
            for(Map.Entry<Long,Pergunta> p : perguntas.getCatPerg().entrySet()){
                if (p.getValue().getOwnerIDp()==449L)
                    {
                        System.out.println(u);
                        novo ++;
                        System.out.println(novo);
                    }
                    else break;
                

            }

            list = this.perguntas.getCatPerg().entrySet().stream()
            .filter(e->(e.getValue().getPergDate().compareTo(begin)>=0 
                && e.getValue().getPergDate().compareTo(end)<=0 
                && e.getValue().getOwnerIDp()==u.getUserID()))
            .map(e->e.getValue())
            .collect(Collectors.toCollection(ArrayList :: new));

            Tag tag;
            Map<String,Tag> map_tags = new HashMap<String,Tag>();
            
            for(Pergunta set : list){
                System.out.println(set.getTitle());
        
                if(set!=null)
                for(String tagName : set){
                
                    System.out.println(tagName);
                    if(map_tags.containsKey(tagName)){
                        map_tags.get(tagName).incTagUse();                        
                    }
                    else {
                        tag = new Tag();
                        //tag.setName(tag);
                        tag.incTagUse();
                        map_tags.put(tagName,tag);
                    }
                }
                
            }

            String maxTag=null;
            Tag tagObj = null;
            long maxUses = 0;
            for(Map.Entry<String,Tag> entry : map_tags.entrySet()){
                if (entry.getValue().getTagUses()>maxUses){
                    tagObj = entry.getValue();
                    maxTag = entry.getKey();
                    maxUses = entry.getValue().getTagUses();
                }
            }
            best_tags.put(maxTag,tagObj);

        }
        Comparator<Map.Entry<String,Tag>> comparador2 = new ComparadorTags();
        //ordena por uses
        List<String> list_ord = new ArrayList<String>();
        list_ord = best_tags.entrySet().stream()
                              .sorted(comparador2)
                              .map(e->e.getKey())
                              .collect(Collectors.toCollection(ArrayList :: new));
                            
        List<Long> list_final = new ArrayList<Long>();
        for(String str : list_ord){
            Long id;
            id = this.tags.getCatTags().get(str);
                                          
            list_final.add(id);
        }                       

        return list_final;
        //return Arrays.asList(6L,29L,72L,163L,587L);
    }
        */
    

    public void clear(){
        this.users.clearCatUsers();
        this.perguntas.clearCatPerg();
        this.respostas.clearCatResp();
        this.tags.clearCatTags();
        return;
    }
}
