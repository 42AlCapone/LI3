package engine;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.System;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class Parser {

	public static void parseUsers(String dump, CatUsers users) {
		
		String xml,file;
		xml = "Users.xml";
		file = dump.concat(xml);


		File inputFile = new File(file);
        try {

        DocumentBuilderFactory dbFactory
                = DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        Document doc = null;

            doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("row");

            Node id=null;
            Node name=null, bio=null;
            Node rep=null, views=null; 
            Node upv=null, downv=null;
            
            Long id1;
            int rep1, views1, upv1, downv1, voteDif;
            String name1, bio1;
            
            User u = null;
            
            for(int x=0,size = nodeList.getLength(); x<size; x++) {
            
            	u = new User();

                id1=null; 
                rep1=views1=voteDif=upv1=downv1=0;
                name1=bio1=null;

                id = nodeList.item(x).getAttributes().getNamedItem("Id");
                name = nodeList.item(x).getAttributes().getNamedItem("DisplayName");
                bio = nodeList.item(x).getAttributes().getNamedItem("AboutMe");
                rep = nodeList.item(x).getAttributes().getNamedItem("Reputation");
                views = nodeList.item(x).getAttributes().getNamedItem("Views");
                upv = nodeList.item(x).getAttributes().getNamedItem("UpVotes");
                downv = nodeList.item(x).getAttributes().getNamedItem("DownVotes");
                
                if(id!=null)
                    id1 = Long.valueOf(id.getNodeValue());
                
                if(name!=null)
                    name1 = name.getNodeValue();
                
                if(bio!=null)
                    bio1 = bio.getNodeValue();
                
                if(rep!=null)
                    rep1 = Integer.parseInt(rep.getNodeValue());
                
                if(views!=null)
                    views1 = Integer.parseInt(views.getNodeValue());
                
                if(upv!=null)
                    upv1 = Integer.parseInt(upv.getNodeValue());
                
                if(downv!=null)
                    downv1 = Integer.parseInt(downv.getNodeValue());
                
                voteDif = upv1-downv1;
      
                u.setUserID(id1);
                u.setName(name1);
                u.setBio(bio1);
                u.setRep(rep1);
                u.setViews(views1);
                u.setVotes(voteDif);

                users.insereUser(u);

                
            }
            System.out.println("USERS INSERIDOS!");
            
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

	}

    


	public static void parsePosts(String dump, CatUsers users, CatPerguntas pergs, CatRespostas resps) {
		
		String xml,file;
		xml = "Posts.xml";
		file = dump.concat(xml);


		File inputFile = new File(file);
        try {

        DocumentBuilderFactory dbFactory
                = DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        Document doc = null;

            doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("row");

            String type;
            
            //Pergunta
            Node idP, dateP, scoreP, ownerIDp, title, tags, nrComsP;

            Long id1, owner1;
            int score1, nrComs1;
            String date1, title1, tags1;
            
            //Resposta
            Node idR, parentID, dateR, scoreR, ownerIDr, nrComsR;

            Long id2, owner2, parent2;
            int score2, nrComs2;
            String date2;

            
            Pergunta p = null;
            Resposta r = null;

            	type=null;
            	idP=dateP=scoreP=ownerIDp=title=tags=nrComsP=null;
            	idR=parentID=dateR=scoreR=ownerIDr=nrComsR=null;


                id1=id2=owner1=owner2=parent2=null;
                score1=score2=nrComs1=nrComs2=0;
                date1=date2=title1=tags1=null;

            for(int x=0,size = nodeList.getLength(); x<size; x++) {

                type = nodeList.item(x).getAttributes().getNamedItem("PostTypeId").getNodeValue();

                if(type.equals("1")){
                    idP = nodeList.item(x).getAttributes().getNamedItem("Id");
                    dateP = nodeList.item(x).getAttributes().getNamedItem("CreationDate");
                    scoreP = nodeList.item(x).getAttributes().getNamedItem("Score");
                    ownerIDp = nodeList.item(x).getAttributes().getNamedItem("OwnerUserId");
                    title = nodeList.item(x).getAttributes().getNamedItem("Title");
                    tags = nodeList.item(x).getAttributes().getNamedItem("Tags");
                    nrComsP = nodeList.item(x).getAttributes().getNamedItem("CommentCount");
                    
                    if(idP!=null)
                        id1 = Long.valueOf(idP.getNodeValue());

                    if(dateP!=null)
                        date1 = dateP.getNodeValue();

                    if(scoreP!=null)
                        score1 = Integer.parseInt(scoreP.getNodeValue());

                    if(ownerIDp!=null)
                        owner1 = Long.valueOf(ownerIDp.getNodeValue());

                    if(title!=null)
                        title1 = title.getNodeValue();

                    if(tags!=null)
                        tags1 = tags.getNodeValue();

                    if(nrComsP!=null)
                        nrComs1 = Integer.parseInt(nrComsP.getNodeValue());

                    p = new Pergunta();
                    p.setPergID(id1);
                    p.setPergDate(convertDate(date1));
                    p.setScoreP(score1);
                    p.setOwnerIDp(owner1);
                    p.setTitle(title1);
                    p.setCommentsP(nrComs1);
                    
                    for(String tag : strToTag(tags1))
                        p.setTag(tag);
                    
                    pergs.inserePerg(p);
                    
                    //INCREMENTAR NRPOSTS
                    users.getUser(owner1).incNrPosts();

                    /*
                    u = users.getUser(owner1);

                    List<String> list = new ArrayList<String>();
                    list = parseTags(tags1);
                    for (String tag : list){
                        if (u.getUserTags().containsKey(tag))
                            u.incrTagUse(tag);
                        else
                            u.setUserTag(tag);
                    }
                    */
                }

                else if (type.equals("2")){

                    idR = nodeList.item(x).getAttributes().getNamedItem("Id");
                    parentID = nodeList.item(x).getAttributes().getNamedItem("ParentId");
                    dateR = nodeList.item(x).getAttributes().getNamedItem("CreationDate");
                    scoreR = nodeList.item(x).getAttributes().getNamedItem("Score");
                    ownerIDr = nodeList.item(x).getAttributes().getNamedItem("OwnerUserId");
                    nrComsR = nodeList.item(x).getAttributes().getNamedItem("CommentCount");
    
                    if(idR!=null)
                        id2 = Long.valueOf(idR.getNodeValue());

                    if(parentID!=null)
                        parent2 = Long.valueOf(parentID.getNodeValue());

                    if(dateR!=null)
                        date2 = dateR.getNodeValue();

                    if(scoreR!=null)
                        score2 = Integer.parseInt(scoreR.getNodeValue());

                    if(ownerIDr!=null)
                        owner2 = Long.valueOf(ownerIDr.getNodeValue());

                    if(nrComsR!=null)
                        nrComs2 = Integer.parseInt(nrComsR.getNodeValue());

                    r = new Resposta();
                    r.setRespID(id2);
                    r.setParentID(parent2);
                    r.setRespDate(convertDate(date2));
                    r.setScoreR(score2);
                    r.setOwnerIDr(owner2);
                    r.setCommentsR(nrComs2);
                    r.setRate(calcRate(users,owner2,score2,nrComs2));
                    resps.insereResp(r);
                    
                    //INSERIR NO SET A RESPOSTA
                    p = pergs.getPergunta(parent2);
                    if(p!=null)
                    p.setResposta(r);

                    //INCREMENTAR nrPOSTS
                	users.getUser(owner2).incNrPosts();

                }

            }
            System.out.println("POSTS INSERIDOS!");

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


    }

    public static double calcRate(CatUsers users, Long oid, int score, int cCount){
        int repUser = users.getUser(oid).getRep();
        return (double) ((score*0.65) + (repUser*0.25) + (cCount*0.1));
    }

    public static LocalDate convertDate(String date) {
    	
    	String[] tokens = date.split("T");
    	String format = tokens[0];
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localdate = LocalDate.parse(format, formatter);

    	return localdate;
    }

    public static List<String> strToTag(String tags) {
        
        String[] tokens = tags.split("<|\\><|\\>");
        List<String> list = new ArrayList<String>();
        for(String tag : tokens)
            list.add(tag);

        return list;
    }

    public static void parseTags(String dump,CatTags tags){
        
        String xml,file;
        xml = "Tags.xml";
        file = dump.concat(xml);


        File inputFile = new File(file);
        try {

        DocumentBuilderFactory dbFactory
                = DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        Document doc = null;

            doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("row");

            Node idNode, nameNode;
            idNode=nameNode=null;

            Long id;
            String name;
        
            
            for(int x=0,size = nodeList.getLength(); x<size; x++) {
            
                id=null;
                name=null;
                
                idNode = nodeList.item(x).getAttributes().getNamedItem("Id");
                nameNode = nodeList.item(x).getAttributes().getNamedItem("TagName");
                                
                if(idNode!=null)
                    id = Long.valueOf(idNode.getNodeValue());
                
                if(nameNode!=null)
                    name = nameNode.getNodeValue();
                
                tags.insereTag(name,id);

                
            }
            System.out.println("TAGS INSERIDAS!");
            
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

}


