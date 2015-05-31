//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.31 at 11:53:00 AM BST 
//


package uk.org.whitecottage.ea.gnosis.jaxb.framework;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uk.org.whitecottage.ea.gnosis.jaxb.framework package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Description_QNAME = new QName("", "description");
    private final static QName _Source_QNAME = new QName("", "source");
    private final static QName _Notes_QNAME = new QName("", "notes");
    private final static QName _BusinessContext_QNAME = new QName("", "businessContext");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uk.org.whitecottage.ea.gnosis.jaxb.framework
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProcessInstance }
     * 
     */
    public ProcessInstance createProcessInstance() {
        return new ProcessInstance();
    }

    /**
     * Create an instance of {@link Milestone }
     * 
     */
    public Milestone createMilestone() {
        return new Milestone();
    }

    /**
     * Create an instance of {@link Framework }
     * 
     */
    public Framework createFramework() {
        return new Framework();
    }

    /**
     * Create an instance of {@link BusinessOperatingModel }
     * 
     */
    public BusinessOperatingModel createBusinessOperatingModel() {
        return new BusinessOperatingModel();
    }

    /**
     * Create an instance of {@link Organisation }
     * 
     */
    public Organisation createOrganisation() {
        return new Organisation();
    }

    /**
     * Create an instance of {@link Group }
     * 
     */
    public Group createGroup() {
        return new Group();
    }

    /**
     * Create an instance of {@link Company }
     * 
     */
    public Company createCompany() {
        return new Company();
    }

    /**
     * Create an instance of {@link Division }
     * 
     */
    public Division createDivision() {
        return new Division();
    }

    /**
     * Create an instance of {@link BusinessUnit }
     * 
     */
    public BusinessUnit createBusinessUnit() {
        return new BusinessUnit();
    }

    /**
     * Create an instance of {@link BusinessProcesses }
     * 
     */
    public BusinessProcesses createBusinessProcesses() {
        return new BusinessProcesses();
    }

    /**
     * Create an instance of {@link ProcessDomain }
     * 
     */
    public ProcessDomain createProcessDomain() {
        return new ProcessDomain();
    }

    /**
     * Create an instance of {@link Process }
     * 
     */
    public Process createProcess() {
        return new Process();
    }

    /**
     * Create an instance of {@link ProcessComponent }
     * 
     */
    public ProcessComponent createProcessComponent() {
        return new ProcessComponent();
    }

    /**
     * Create an instance of {@link ProcessFlow }
     * 
     */
    public ProcessFlow createProcessFlow() {
        return new ProcessFlow();
    }

    /**
     * Create an instance of {@link Parent }
     * 
     */
    public Parent createParent() {
        return new Parent();
    }

    /**
     * Create an instance of {@link Predecessor }
     * 
     */
    public Predecessor createPredecessor() {
        return new Predecessor();
    }

    /**
     * Create an instance of {@link ProcessInstance.DataImpact }
     * 
     */
    public ProcessInstance.DataImpact createProcessInstanceDataImpact() {
        return new ProcessInstance.DataImpact();
    }

    /**
     * Create an instance of {@link BusinessApplications }
     * 
     */
    public BusinessApplications createBusinessApplications() {
        return new BusinessApplications();
    }

    /**
     * Create an instance of {@link TechnologyDomain }
     * 
     */
    public TechnologyDomain createTechnologyDomain() {
        return new TechnologyDomain();
    }

    /**
     * Create an instance of {@link Capability }
     * 
     */
    public Capability createCapability() {
        return new Capability();
    }

    /**
     * Create an instance of {@link CommonServices }
     * 
     */
    public CommonServices createCommonServices() {
        return new CommonServices();
    }

    /**
     * Create an instance of {@link Infrastructure }
     * 
     */
    public Infrastructure createInfrastructure() {
        return new Infrastructure();
    }

    /**
     * Create an instance of {@link ValueChain }
     * 
     */
    public ValueChain createValueChain() {
        return new ValueChain();
    }

    /**
     * Create an instance of {@link PrimaryActivities }
     * 
     */
    public PrimaryActivities createPrimaryActivities() {
        return new PrimaryActivities();
    }

    /**
     * Create an instance of {@link Activity }
     * 
     */
    public Activity createActivity() {
        return new Activity();
    }

    /**
     * Create an instance of {@link Ecosystem }
     * 
     */
    public Ecosystem createEcosystem() {
        return new Ecosystem();
    }

    /**
     * Create an instance of {@link CapabilityInstance }
     * 
     */
    public CapabilityInstance createCapabilityInstance() {
        return new CapabilityInstance();
    }

    /**
     * Create an instance of {@link SupportActivities }
     * 
     */
    public SupportActivities createSupportActivities() {
        return new SupportActivities();
    }

    /**
     * Create an instance of {@link RecycleBin }
     * 
     */
    public RecycleBin createRecycleBin() {
        return new RecycleBin();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "description")
    public JAXBElement<String> createDescription(String value) {
        return new JAXBElement<String>(_Description_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "source")
    public JAXBElement<String> createSource(String value) {
        return new JAXBElement<String>(_Source_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "notes")
    public JAXBElement<String> createNotes(String value) {
        return new JAXBElement<String>(_Notes_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "businessContext")
    public JAXBElement<Object> createBusinessContext(Object value) {
        return new JAXBElement<Object>(_BusinessContext_QNAME, Object.class, null, value);
    }

}
