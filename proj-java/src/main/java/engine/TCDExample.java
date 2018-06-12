/**
 * Classe de implementação das querys.
 *
 * @author António Gomes
 * @author João Vieira
 * @author Rui Vieira
 * @version 1.6
 */

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

    /**
     * Construtor por omissao de TCDExmaple.
     * <p>
     * Inicializa todos os parametros de TCDExample atravez dos seus construtores por omissao.
     * <p>
     * users e inicializado com o metodo CatUsers() de CatUsers.java.
     * <p>
     * perguntas e inicializado com o metodo CatPerguntas() de CatPerguntas.java.
     * <p>
     * respostas e inicializado com o metodo CatRespostas() de CatRespostas.java.
     * <p>
     * tags e inicializado com o metodo CatTags() de CatTags.java
     *
     * @see CatUsers#CatUsers()
     * @see CatPerguntas#CatPerguntas()
     * @see CatRespostas#CatRespostas()
     * @see CatTags#CatTags()
     */
    public TCDExample() {
        
        this.users = new CatUsers();
        this.perguntas = new CatPerguntas();
        this.respostas = new CatRespostas();
        this.tags = new CatTags();
    }

    /**
     * Método responsável pelo carregamento dos dados para as estruturas.
     * <p>
     * parseUsers(String,CatUsers) carrega os dados correspondentes aos sers para users
     *  {@link Parser#parseUsers}
     * <p>
     * parsePosts(String,CatUsers,CatPerguntas,CatRespostas) carrega dados relativos a Users,
     *  Perguntas e Respostas {@link Parser#parsePosts}
     * <p>
     * parseTags(String,CatTags) carrega dados relativas as tags {@link Parser#parseTags}
     *
     * @see Parser
     */
    public void load(String dumpPath) {
        
        Parser.parseUsers(dumpPath,this.users);
        Parser.parsePosts(dumpPath,this.users, this.perguntas,this.respostas);       
        Parser.parseTags(dumpPath,this.tags);
    }
  

    // Query 1
    /**
     * Query de obtençao de informação relativa a um post (seja pergunta ou resposta).
     * <p>
     * Acede as estruturas de forma a procurar um post com id igual a um long passado por argumento
     * e guarda essa informacao num Par de Strings (Classe Pair).
     * <p>
     * Este método esta dividido em duas partes mutuamente exclusivas (if else)
     * <p>
     * A primeira parte e responsavel por detetar se o post que pretendemos procurar e uma pregunta:
     * <code>if(this.perguntas.getCatPerg().contaisKey(id))</code> obtem o HashMap catPerg de perguntas e
     * acede-o para confirmar se existe alguma entrada com id igual ao long passado por argumento.
     * Caso esse teste seja true guarda a informacao relativa ao post:
     * <code>p = this.perguntas.getCatPerg().get(id)</code> acede a catPerg de perguntas e guarda a Pergunta
     * com chave correspondente a id.
     * <code>u = this.users.getCatUsers().get(p.getOwnerIDp())</code> acede ao HashMap de users e procura pelo
     * user que publicou a pergunta p, o id da pergunta e obtida por getOwnerIDp que e passado ao metodo get
     * aplicado a catUsers de users.
     * por fim a informacao relativa ao titulo da pergunta e ao nome do user e armazenada em duas strings.
     * <p>
     * A segunda parte efetua codigo semelhante a primeira contudo verifica se o post e uma resposta
     * e caso seja, guarda a informacao relativa a pergunta que originou essa resposta e ao user que publicou
     * a pergunta.
     *
     * @param id id do post a ser procurado
     * @return um par <code>Pair</code> de strings contedo o titulo de uma pergunta e o nome do user
     * que a publicou.
     * @see CatPerguntas#getCatPerg
     * @see CatUsers#getCatUsers
     * @see Pergunta#getOwnerIDp
     * @see Pergunta#getTitle
     * @see User#getName
     * @see CatRespostas#getCatResp
     * @see java.util.HashMap
     */
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
    
    /**
     * Query de procura dos N utilizadores mais ativos.
     * <p>
     * Armazena numa List os N utilizadores mais ativos de entre os presentes em Users.
     * <p>
     * De modo a procurar todos os utilizadores a estrutura e precorrida atravez do uso de uma stream
     * de eventos (de modo a aumentar a eficiencia).
     * <p>
     * <code>Comparator comparador = new ComparadorNrPosts()</code> serve de lei de comparacao
     * entre users tendo como parametro de comparacao o numero de contribuicoes.
     * <p>
     * list = ... a lista de resultados i sera igual ao resultado de aplicar uma serie de operacoes
     * em sequencia alimentadas por uma stream de eventos.
     * <p>
     * <code>this.users.getCatUsers()</code> obtem o HashMap catUsers de users.
     * <p>
     * <code>entrySet()</code> "transforma" o  HashMap num set itravel.
     * <p>
     * <code>stream()</code> cria uma stream de eventos do entrySet alimentando os metodos seguintes
     * com os eventos de entrySet em sequencia sem dependerem uns dos outros, dai que a medida que novas
     * entadas do set sao aliemntadas a stream as anteriores ja estao a ser processadas pelos diversos metods.
     * <p>
     * <code>sorted(comparador)</code> utilizando o comparador como regra de comparacao de users, este metodo
     * ordena os eventos que lhe sao alimentados.
     * <p>
     * <code>limit(N)</code> introduz um limite maximo de eventos que se pode ter, isto e, apenas aproveita os
     * N primeiros eventos (que sao os N maiores gracas a sorted)
     * <p>
     * <code>map(e->e.getValue().getUserID())</code> para cada entrada (chave valor) este metodo aproveita
     * apenas o UserID de cada user que recebe.
     * <p>
     * <code>collect(Collectors.toCollection(ArrayList :: new))</code> armazena os eventos que recebe de novo.
     *
     * @param N numero de IDs que pretendemos obter.
     * @return a lista dos ids dos users com mais contribuicoes ordenada.
     * @see ComparadorNrPosts
     * @see java.util.ArrayList
     * @see User#getUserID
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

    //Query 7
    /**
     * Query de contagem do numero total de posts entre um determinado intervalo de tempo.
     * <p>
     * este metodo esta dividido em duas partes:
     * <p>
     * A primeira parte visa a precorres as perguntas atravez de um foreach e incrementa uma
     * vairavel interia perg cada vez que encontra uma pergunta dentro do intervalo.
     * <code>if(p.getPergDate().compareTo(begin) && p.getPergDate().compareTo(end)</code>
     * Esta porçao de codigo acede a pergunta p (cada pergunta gracas a ao for each) obtem a data
     * da pertunta e compara respectivamente entre o begin e o end.
     * <p>
     * A segunda parte efetua codigo semelhante a primeira com a diferenca de iterar sobre as respostas
     * e incrementar uma variavel inteira correspondente as respostas.
     *
     * @param begin Data inicial do intervalo
     * @param end Data filan do intervalo
     * @return Par de Longs correspondentes a contagem de perguntas e respostas.
     * @see Pergunta#getPergDate
     * @see java.time.LocalDate
     */
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

    //Query 4
    /**
     * Query de procura de perguntas que possuem uma determinada tag e se encontram dentro de
     * um determinado intervalo de tempo.
     * <p>
     * Neste metodo e utilizado um Comparator comparador que serve como regra de ordenacao das perguntas.
     * <p>
     * <code>this.perguntas.getCatPerg().entrySet().stream()</code> acede a catPerg de perguntas geta um
     * set iteravel de catPerg e inicia uma stream de eventos deste set.
     * <p>
     * Filter(perdicado) cada entrada da stream apenas segue para os proximos metodos se passar
     * no teste Perdicado.
     * <p>
     * <code>e.getValue().getTags().contains(tag)</code> porcao do perdicado que testa se uma string tag esta
     * nas tags da pergunta correspondente ao evento e (cada evento da stream)
     * <p>
     * <code>e.getValue().getPergDate().comapreTo(begin)</code> porcao do perdicado que testa se uma pergunta
     * foi publicada depois de uma determinada data begin.
     * <p>
     * <code> <p>e.getValue()getPertDate().compareTo(end)</code> porcao do perdicado que testa se uma 
     * pergunta foi publicada antes de uma denterminada data end.
     * <p>
     * apenas eventos que passam em todas as tres porcoes do perdicado podem prosseguir para os proximos
     * metodos.
     * <p>
     * <code>sorted(comparador)</code> ordena os eventos segundo a regra defenida em comparador
     * {@link ComparadorDatesP}
     * <p>
     * <code>map(e.getValue().getPergID)</code> para cada evento (Pergunta) aproveita apenas o seu id.
     * <p>
     * <code>collect(collectors.toCollection(ArrayList :: new))</code> armazena os eventos (ja ordenadados)
     *
     * @param tag String correspondente a tag a procurar
     * @param begin data inicial do intervalo de tempo
     * @param end data final do intervalo de tempo
     * @return lista ordenada dos ids das perguntas que possuem a tag e foram publicadas dentro do
     * intervalo de tempo begin end.
     * @see CatPerguntas#getCatPerg
     * @see ComparadorDatesP
     */
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

    //Query 5
    /**
     * Query de procura de dados relativos a um user.
     * <p>
     * Dados do user correspondem ao seu bio e aos 10 primeiros posts por ele efectuados.
     * <p>
     * Este metodo esta dividido em 5 partes.
     * <p>
     * Primeira parte cria os 3 comparadores que servirao como regra de ordenacao entre
     * perguntas, respostas e datas.
     * <p>
     * Segunda parte obtem o bio relativo ao user com id passado por argumento.
     * <p>
     * Terceira parte precorre a estrutura de perguntas com uma stream (this.perguntas.getCatPerg().
     * entrySet().stream()), filtra as perguntas que possuem userID igual ao id de argumento (filter
     * (e.getValue().getOwnerIDp()==id)), ordena os eventos segundo a regra comparadorP, (sorted(comparadorp)),
     * retira os 10 primeiros que gracas a ordenacao correspondem as 10 menores datas (limit(10)),
     * dos eventos obtem apenas as perguntas (map(e.getValue())), arazenas as perguntas
     * (collect(Collectors.toCollection(ArrayList :: new))).
     * <p>
     * A quarta parte realiza uma tarefa semelhante a terceira mas precorrendo as respostas.
     * <p>
     * A quinta parte introduz os ids das 10 perguntas e 10 respostas obtidas nas partes 3 e 4 todas na mesma
     * estrutura, atravez de uma stream de eventos ordena-as por data segundo a regra comparadorL e obtem os
     * ids dos 10 primeiros elementos da estrutura resultado, obtendo por isso os ids dos 10 primeiros
     * posts efectuados por esse user (sejam perguntas ou respostas).
     *
     * @param id id do user a obter informacao.
     * @return um par string List correspondete ao bio do utilizador e a lista ordenada dos 10 primeiros
     * posts efectuados por esse utilizador.
     * @see ComparadorDatesP
     * @see ComparadorDatesR
     * @see ComparadorDatesL
     * @see User#getBio
     */
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

    //Query 6
    /**
     * Query de procura das N respostas mais votadas.
     * <p>
     * Atravez do uso de uma stream sobre as respostas esta query obtem as N respostas com melhor score
     * obtidas entre um intevalo de tempo. Esta operacao possui os seguinte passos:
     * <p>
     * <code>this.respostas.getCatResp().entrySet().stream()</code> acede a catResp de respostas, cria um
     * set iteravel desse HashMap e gera uma stream de eventos de tais respostas.
     * <p>
     * <code>filter(perdicado)</code> filtra os eventos que recebe deixando passar apenas os eventos que
     * passam no perdicado.
     * <p>
     * <code>e.getValue().getRespDate().compareTo(begin)</code> acede a data do evento e compara com begin
     * devolvendo true caso este tenha sido publicado apos a data begin (ou igual).
     * <p>
     * <code>e.getValue().getRespDate().compareTo(end)</code> acede a data do evento e compara com end 
     * devolvendo true caso este tenha sido publicado antes de end (ou igual).
     * <p>
     * <code>sorted(comparador)</code> ordena os eventos segundo a regra definida em {@link ComparadorScore}
     * <p>
     * <code>limit(N)</code> obtem os 10 primeiros eventos (ja ordenados).
     * <p>
     * <code>map(e.getValue().getRespID())</code> obtem o id da resposta (value) do evento.
     * <p>
     * <code>collect(Collectors.toCollection(ArrayList :: new))</code> armazena os eventos de novo.
     *
     * @param N numero de respostas a obter.
     * @param begin data inicial do intervalo.
     * @param end data final do intervalo.
     * @return lista ordenada por score das respostas mais votadas.
     * @see CatRespostas#getCatResp
     * @see Resposta#getRespDate
     * @see Resposta#getRespID
     */
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

    //Query 7
    /**
     * Query de procura das N perguntas mais respondidas.
     * <p>
     * Atravéz de um mecanismo de java stream bastante semelhante ao utilizado em querys antreiores,
     * as Perguntas sao precorridas atravez de uma stream de eventos, estas sao filtradas a partir da sua
     * data (data pertence ao intervalo) passando apenas os eventos cujo perdicado retorna true, os eventos
     * restantes sao ordenados segundo a regra definida em {@link ComparadorNrResps}, os N primeiros sao
     * obtidos, sao retirados os ids das Perguntas dos eventos (id dos values) e por final os ids sao
     * armazenados.
     *
     * @param N numero de perguntas a obter.
     * @param begin data inicial do intervalo.
     * @param end data final do intervalo.
     * @return Lista ordenada dos ids das perguntas mais respondidas.
     * @see ComparadorNrResps
     * @see CatPerguntas#getCatPerg
     * @see Pergunta#getPergDate
     * @see Pergunta#getPergID
     */
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

    //Query 8
    /**
     * query de procura de N perguntas que conteem uma determinada palavra no seu titulo.
     * <p>
     * Utiliza uma estrategia de java stream semelhante a outras querys.
     * <p>
     * a filtragem tem como perdicado a existencia da palavra word no titulo.
     * <p>
     * A ordenacao e feita segundo a regra comparadorDatesP
     * <p>
     * limit(N) obtem-se apenas os n primeiros.
     * <p>
     * O map retira o id da pergunta
     * <p>
     * collect armazena os eventos.
     *
     * @param N numero de perguntas a obter.
     * @param word palavra a procurar.
     * @return lista ordenada por datas das N primeiras perguntas que conteem a palavra N.
     * @see ComparadorDatesP
     * @see CatPerguntas#getCatPerg
     * @see Pergunta#getTitle
     * @see Pergunta#getPergID
     */
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
    /**
     * Query de procura de posts em que dois utilizadores participaram.
     * <p>
     * Este metodo esta dividido me 7 partes.
     * <p>
     * Primeira parte coleciona as perguntas pertencentes ao user 1 atravez de uma estrategia de java stream
     * semelhante ao efectuado em metodos anteriores. O perdicado do filter e a comparacao do ownerUserID
     * da pergunta com o id1 passado por argumento.
     * <p>
     * map obtem a pergunta de dentro do evento.
     * <p>
     * collect armazena as perguntas em persUser1.
     * <p>
     * A segunda parte efectua uma tarefa semelhante a primeira porem tendo em consideracao o user2 em vez
     * do user1 armazenando as suas perguntas em PergsUser2.
     * <p>
     * A terceira Parte inicializa tres estruturas auxiliares. Um map de perguntas e dois sets de respostas
     * set1 e set2.
     * <p>
     * A quarta Parte trata do caso de user1 aparecer na pergunta e user2 na resposta.
     * Utiliza um foreach sobre as perguntas do user1 (pergsUser1) e para cada uma precorre as suas respostas
     * a procura de respostas cujo owner e o user2 (mais um foreach sobre o set1 que e correspondido ao
     * set de respostas da pergunta). Assim que a primeira resposta e encontrada a pergunta e inserida no map.
     * <p>
     * A quita parte trata do caso de user2 aparcer na pergunta e user1 na resposta.
     * Efetua uma tarefa igual a parte 4 mas iterando sobre as perguntas do user 2 a procura de respostas
     * do user1. 
     * <p>
     * A sexta parte trata do caso de ambos os users surgirem em respostas.
     * Todas as pergutnas sao iteradas segundo um forach sobre o HashMap de perguntas. Para cada pergunta
     * e precorrido o seu set de respostas a procura de ambos os users atravez de um foreach sobre o set3
     * correspondete ao set de respostas da pergunta.
     * Uma flag inteira e utilizada para se saber quantos dos 2 users ja se encotrou.
     * Caso a flag seja igual a 2 significa que ambos os users ja foram encontrados e que o id da pergunta ja
     * foi armazemado pelo que se pode prosseguir para a proxima pergunta.
     * Caso a flag esteja a 1 entao significa que um dos users ja foi encontrado pelo que e nessessario
     * verificar se a resposta atual foi efectuada por um dos users, caso seja verdade a pergunta e armazenada
     * no map e a flag e incrementada.
     * Caso a flag esteja a 0 e caso a resposta atual possua owner igual a um dos users entao a flag
     * e incrementada.
     * Esta sequencia de operacoes introduz no map as perguntas que ambos os users surgem como resposta.
     * <p>
     * A setima parte consiste em precorrer o map de respostas atravez de uma stream de eventos a semelhanca
     * de metodos anteriores.
     * Os eventos sao ordenados segundo a regra definida em {@link ComparadorDatesP}, sao obtidos os N
     * primeiros elementos, de cada elemento obtem-se o seu id que sao por fim armazenados ja ordenados
     * obtendo por isso a lista dos ids dos posts em que ambos os utilizadores participaram.
     *
     * @param N numero de posts a obter.
     * @param id1 id do user 1
     * @param id2 id do user 2
     * @return lista ordenada por data dos ids das perguntas em que ambos os users participaram (no post).
     * @see CatPerguntas#getCatPerg
     * @see Pergunta#getOwnerIDp
     * @see Resposta#getOwnerIDr
     * @see Pergunta#getPergID
     * @see Pergunta#getRespostas
     * @see ComparadorDatesP
     */
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
    /**
     * Query de procura da melhor resposta da pergunta com id passado por argumento.
     * <p>
     * Caso o id passado nao exista no HashMap de perguntas este metodo retorna -1.
     * <p>
     * A procura sera efectuada sobre o set de respostas da pergunta cujo id e passado por argumento.
     * <p>
     * Com o uso de um for each para cada resposta deste set e comparado o seu rate com o rate da melhor
     * resposta encontrada ate agora, caso esta resposta seja melhor entao o seu id e rate sao guardados
     * como sendo a nova melhor resposta.
     * <p>
     * O resultado e o id da melhor resposta encontrada.
     *
     * @param id id da pergunta alvo.
     * @return id da resposta da pergunta com maior rate.
     * @see CatPerguntas#getCatPerg
     * @see Pergunta#getRespostas
     * @see Resposta#getRate
     * @see Resposta#getRespID
     */
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
    

    /**
     * Metodo de limpeza das estruturas.
     * <p>
     * Utiliza os metodos clear de cada uma das estruturas catUsers, catPerg, CatResp e catTags para efectuar
     * a limpeza das mesmas.
     *
     * @see CatUsers#clearCatUsers
     * @see CatPerguntas#clearCatPerg
     * @see CatRespostas#clearCatResp
     * @see CatTags#clearCatTags
     */
    public void clear(){
        this.users.clearCatUsers();
        this.perguntas.clearCatPerg();
        this.respostas.clearCatResp();
        this.tags.clearCatTags();
        return;
    }
}
