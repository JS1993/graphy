package com.peploleum.insight.graphy;

import com.microsoft.spring.data.gremlin.common.GremlinFactory;
import com.peploleum.insight.graphy.domain.Network;
import com.peploleum.insight.graphy.domain.Person;
import com.peploleum.insight.graphy.domain.Relation;
import com.peploleum.insight.graphy.repository.NetworkRepository;
import com.peploleum.insight.graphy.repository.PersonRepository;
import com.peploleum.insight.graphy.repository.RelationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class GraphyApplication {
    private static final String PERSON_ID = "89757";
    private static final String PERSON_ID_0 = "0123456789";
    private static final String PERSON_ID_1 = "666666";
    private static final Long LONG_PERSON_ID = 12L;
    private static final Long LONG_PERSON_ID_0 = 13L;
    private static final Long LONG_PERSON_ID_1 = 14L;
    private static final String PERSON_NAME = "person-name";
    private static final String PERSON_NAME_0 = "person-No.0";
    private static final String PERSON_NAME_1 = "person-No.1";
    private static final String PERSON_AGE = "4";
    private static final String PERSON_AGE_0 = "18";
    private static final String PERSON_AGE_1 = "27";

    private static final String RELATION_ID = "2333";
    private static final Long LONG_RELATION_ID = 2333L;
    private static final String RELATION_NAME = "brother";

    //    private final Person person = new Person(PERSON_ID, PERSON_NAME, PERSON_AGE);
    private final Person person = new Person();
    private final Person person0 = new Person();
    private final Person person1 = new Person();
    private final Relation relation = new Relation();
    private final Network network = new Network();

    private final Logger log = LoggerFactory.getLogger(GraphyApplication.class);

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private RelationRepository relationRepo;

    @Autowired
    private NetworkRepository networkRepo;

    @Autowired
    private GremlinFactory factory;

    public static void main(String[] args) {
        SpringApplication.run(GraphyApplication.class, args);
    }

    @PostConstruct
    public void setup() {
        try {
            this.networkRepo.deleteAll();
        } catch (Exception e) {
            this.log.error(e.getMessage(), e);
        }

        this.person.setAge(PERSON_AGE);
        this.person0.setAge(PERSON_AGE_0);
        this.person1.setAge(PERSON_AGE_1);
        this.person.setName(PERSON_NAME);
        this.person0.setName(PERSON_NAME_0);
        this.person1.setName(PERSON_NAME_0);

        final Person savedPerson = this.personRepo.save(this.person);
        final Person savedPerson0 = this.personRepo.save(this.person0);
        final Person savedPerson1 = this.personRepo.save(this.person1);

        this.network.getVertexes().add(savedPerson);
        this.network.getVertexes().add(savedPerson0);
        this.network.getVertexes().add(savedPerson1);
//
        this.relation.setName(RELATION_NAME);
        this.relation.setPersonFrom(savedPerson);
        this.relation.setPersonTo(savedPerson0);

        final Relation savedRelation = this.relationRepo.save(this.relation);
        this.network.getEdges().add(savedRelation);
        final Network saved = this.networkRepo.save(this.network);
    }
}

