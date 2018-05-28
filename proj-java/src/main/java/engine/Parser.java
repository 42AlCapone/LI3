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

public class Parser {

	public static void parseUsers(String dump) {
		
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
            
            for(int x=0,size = nodeList.getLength(); x<size; x++) {
                
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
      
                System.out.println(id1);
                System.out.println(name1);
                System.out.println(bio1);
                System.out.println(rep1);
                System.out.println(views1);
                System.out.println(voteDif);                
                System.out.println('\n');
            }
            

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

	}



	public static void parsePosts(String dump) {
		
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

            String type=null;
            
            //Pergunta
            Node idP, dateP, scoreP, ownerIDp, title, tags, nrComsP;
            idP=dateP=scoreP=ownerIDp=title=tags=nrComsP=null;

            Long id1, owner1;
            int score1, nrComs1;
            String date1, title1, tags1;
            
            //Resposta
            Node idR, parentID, dateR, scoreR, ownerIDr, nrComsR;
            idR=parentID=dateR=scoreR=ownerIDr=nrComsR=null;

            Long id2, owner2, parent2;
            int score2, nrComs2;
            String date2;

            for(int x=0,size = nodeList.getLength(); x<size; x++) {
                
                id1=id2=owner1=owner2=parent2=null;
                score1=score2=nrComs1=nrComs2=0;
                date1=date2=title1=tags1=null;

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

                    System.out.println(id1);
                    System.out.println(date1);
                    System.out.println(score1);
                    System.out.println(owner1);
                    System.out.println(title1);
                    System.out.println(tags1);
                    System.out.println(nrComs1);
                    System.out.println('\n');

                    //INCREMENTAR NRPOSTS

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
                        date2 = dateP.getNodeValue();

                    if(scoreR!=null)
                        score2 = Integer.parseInt(scoreR.getNodeValue());

                    if(ownerIDr!=null)
                        owner2 = Long.valueOf(ownerIDr.getNodeValue());

                    if(nrComsR!=null)
                        nrComs2 = Integer.parseInt(nrComsR.getNodeValue());

                    System.out.println(id2);
                    System.out.println(parent2);
                    System.out.println(date2);
                    System.out.println(score2);
                    System.out.println(owner2);
                    System.out.println(nrComs2);
                    System.out.println('\n');
                    

                    //INCREMENTAR nrPOSTS
                }

                
            }
            

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


    }

}


