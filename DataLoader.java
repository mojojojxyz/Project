/**
 * Builds the hardcoded initial knowledge base for the Career Path Oracle.
 * Contains a pre-built Binary Decision Tree with 30+ tech career leaf nodes,
 * organized by branching logic that separates front-end, data, infrastructure,
 * and engineering roles.
 *
 * This tree is used as the default when no saved data file (careers.txt) exists.
 */
public class DataLoader {

    /**
     * Creates and returns a DecisionTree pre-loaded with 30+ career paths.
     * The tree is structured as a series of Yes/No questions that guide
     * the user from general preferences to a specific career prediction.
     *
     * @return a DecisionTree populated with the initial career knowledge base
     */
    public static DecisionTree loadInitialTree() {

        // ===== ROOT =====
        Node root = new Node("Do you prefer working with visual interfaces (front-end) over back-end logic?");

        // ================================================================
        // YES BRANCH — Frontend / Design
        // ================================================================
        Node aesthetic = new Node("Do you care more about artistic aesthetics than technical precision?");
        root.setYesNode(aesthetic);

        // --- Aesthetic YES branch ---
        Node mobileDesign = new Node("Do you design mobile apps specifically?");
        aesthetic.setYesNode(mobileDesign);
        mobileDesign.setYesNode(new Node("Mobile UI Designer"));
        mobileDesign.setNoNode(new Node("UX/UI Designer"));

        // --- Aesthetic NO branch ---
        Node jsDaily = new Node("Do you enjoy writing JavaScript and CSS daily?");
        aesthetic.setNoNode(jsDaily);

        // jsDaily YES
        Node frameworks = new Node("Do you work with frameworks like React or Vue?");
        jsDaily.setYesNode(frameworks);
        frameworks.setYesNode(new Node("Frontend Developer (React/Vue)"));
        frameworks.setNoNode(new Node("Web Developer"));

        // jsDaily NO
        Node brandIdentity = new Node("Do you create visual brand identities?");
        jsDaily.setNoNode(brandIdentity);
        brandIdentity.setYesNode(new Node("Graphic Designer"));
        brandIdentity.setNoNode(new Node("Product Designer"));

        // ================================================================
        // NO BRANCH — Back-end / Other
        // ================================================================
        Node dataVsLogic = new Node("Do you enjoy working with data more than writing application logic?");
        root.setNoNode(dataVsLogic);

        // ================================================================
        // DATA BRANCH (dataVsLogic YES)
        // ================================================================
        Node mlModels = new Node("Do you build machine learning models?");
        dataVsLogic.setYesNode(mlModels);

        // mlModels YES
        Node researchVsEng = new Node("Do you focus on research over engineering?");
        mlModels.setYesNode(researchVsEng);
        researchVsEng.setYesNode(new Node("AI Research Scientist"));
        researchVsEng.setNoNode(new Node("Machine Learning Engineer"));

        // mlModels NO
        Node visualizeData = new Node("Do you visualize and present data insights?");
        mlModels.setNoNode(visualizeData);
        visualizeData.setYesNode(new Node("Data Analyst / BI Developer"));

        Node designDB = new Node("Do you design and maintain databases?");
        visualizeData.setNoNode(designDB);
        designDB.setYesNode(new Node("Database Administrator"));
        designDB.setNoNode(new Node("Data Engineer"));

        // ================================================================
        // ENGINEERING BRANCH (dataVsLogic NO)
        // ================================================================
        Node infraServers = new Node("Do you prefer managing servers, infrastructure, and deployments?");
        dataVsLogic.setNoNode(infraServers);

        // infraServers YES
        Node cicd = new Node("Do you automate pipelines and CI/CD?");
        infraServers.setYesNode(cicd);
        cicd.setYesNode(new Node("DevOps Engineer"));
        cicd.setNoNode(new Node("Cloud Engineer / SysAdmin"));

        // infraServers NO
        Node coreLogic = new Node("Do you write core application business logic?");
        infraServers.setNoNode(coreLogic);

        // coreLogic YES
        Node mobilePlatform = new Node("Do you work primarily with mobile platforms?");
        coreLogic.setYesNode(mobilePlatform);

        // mobilePlatform YES
        Node iosSwift = new Node("Is it iOS (Swift)?");
        mobilePlatform.setYesNode(iosSwift);
        iosSwift.setYesNode(new Node("iOS Developer"));
        iosSwift.setNoNode(new Node("Android Developer"));

        // mobilePlatform NO
        Node backendAPIs = new Node("Do you prefer back-end APIs and microservices?");
        mobilePlatform.setNoNode(backendAPIs);
        backendAPIs.setYesNode(new Node("Back-End Developer"));
        backendAPIs.setNoNode(new Node("Full-Stack Developer"));

        // coreLogic NO
        Node security = new Node("Do you find and fix security vulnerabilities?");
        coreLogic.setNoNode(security);
        security.setYesNode(new Node("Cybersecurity Engineer"));

        Node qa = new Node("Do you test and ensure software quality?");
        security.setNoNode(qa);
        qa.setYesNode(new Node("QA Engineer / SDET"));

        Node projectMgr = new Node("Do you manage technical projects and teams?");
        qa.setNoNode(projectMgr);
        projectMgr.setYesNode(new Node("IT Project Manager"));

        Node embedded = new Node("Do you build embedded systems or hardware firmware?");
        projectMgr.setNoNode(embedded);
        embedded.setYesNode(new Node("Embedded Systems Engineer"));

        // --- Extended branch from Technical Support ---
        Node networking = new Node("Do you focus on networking and system infrastructure?");
        embedded.setNoNode(networking);
        networking.setYesNode(new Node("Technical Support Engineer"));

        Node blockchain = new Node("Do you work with blockchain or distributed ledger technology?");
        networking.setNoNode(blockchain);
        blockchain.setYesNode(new Node("Blockchain Developer"));

        Node gameDev = new Node("Do you develop video games or interactive simulations?");
        blockchain.setNoNode(gameDev);
        gameDev.setYesNode(new Node("Game Developer"));

        Node arVr = new Node("Do you create augmented or virtual reality experiences?");
        gameDev.setNoNode(arVr);
        arVr.setYesNode(new Node("AR/VR Developer"));

        Node cloudArchitect = new Node("Do you design large-scale cloud architectures?");
        arVr.setNoNode(cloudArchitect);
        cloudArchitect.setYesNode(new Node("Cloud Architect"));

        Node techWriter = new Node("Do you write technical documentation and API guides?");
        cloudArchitect.setNoNode(techWriter);
        techWriter.setYesNode(new Node("Technical Writer"));

        Node scrumMaster = new Node("Do you facilitate agile ceremonies and team processes?");
        techWriter.setNoNode(scrumMaster);
        scrumMaster.setYesNode(new Node("Scrum Master"));

        Node sre = new Node("Do you ensure site reliability and uptime for production systems?");
        scrumMaster.setNoNode(sre);
        sre.setYesNode(new Node("Site Reliability Engineer (SRE)"));
        sre.setNoNode(new Node("Solutions Architect"));

        return new DecisionTree(root);
    }
}
