/**
 * Builds a deeper Binary Decision Tree with 20 tech careers.
 * Tree depth ranges from 7 to 10, with most paths at 8-9.
 * Each leaf stores: career name, detailed English description, Uke/Seme type.
 */
public class DataLoader {

    /**
     * Creates a DecisionTree with 20 careers at depth 7-10.
     * @return a populated DecisionTree
     */
    public static DecisionTree loadInitialTree() {

        // L1 ROOT
        Node root = new Node("Do you prefer building software and writing code over managing infrastructure and hardware?");

        // ================================================================
        // L1 YES — Software & Development (10 careers)
        // ================================================================
        // L2
        Node l2_userFacing = new Node("Do you enjoy creating things that users directly see and interact with on screen?");
        root.setYesNode(l2_userFacing);

        // ------ L2 YES: User-facing (5 careers, depth 8-10) ------
        // L3
        Node l3_apps = new Node("Are you more passionate about building interactive applications than analyzing data and reports?");
        l2_userFacing.setYesNode(l3_apps);

        // L3 YES: App Development (3 careers)
        // L4
        Node l4_notGames = new Node("Do you prefer developing real-world applications over creating video games?");
        l3_apps.setYesNode(l4_notGames);

        // L4 YES: Web/Mobile (2 careers at depth 10)
        // L5
        Node l5_webStack = new Node("Do you prefer working with web technologies (HTML, CSS, JavaScript) over native mobile platforms?");
        l4_notGames.setYesNode(l5_webStack);

        // L5 YES -> deeper web path
        // L6
        Node l6_frameworks = new Node("Do you enjoy building complex UIs with modern frameworks like React, Vue, or Angular?");
        l5_webStack.setYesNode(l6_frameworks);

        // L6 YES
        // L7
        Node l7_responsive = new Node("Do you specialize in creating responsive, accessible web interfaces?");
        l6_frameworks.setYesNode(l7_responsive);

        // L7 YES
        // L8
        Node l8_clientOnly = new Node("Do you focus mainly on the browser side, leaving server-side logic to others?");
        l7_responsive.setYesNode(l8_clientOnly);

        // L8 YES/NO -> depth 9
        l8_clientOnly.setYesNode(new Node("Frontend Developer",
                "Builds beautiful, responsive web interfaces using HTML, CSS, and JavaScript with modern frameworks like React or Vue. Focuses on user experience, accessibility, and performance in the browser.",
                "Uke"));
        l8_clientOnly.setNoNode(new Node("Full Stack Developer",
                "Handles both frontend interfaces AND backend servers. Comfortable with React/Vue on the client side and Node.js/Python/Java on the server side. A one-person army who can build complete web applications.",
                "Seme"));

        // L7 NO
        // L8
        Node l8_backend = new Node("Do you prefer writing server-side APIs and business logic over working with databases directly?");
        l7_responsive.setNoNode(l8_backend);
        l8_backend.setYesNode(new Node("Backend Developer",
                "Builds server-side applications, RESTful APIs, and microservices. Works with languages like Java, Python, Go, or Node.js. Handles authentication, business logic, and system integration.",
                "Seme"));
        l8_backend.setNoNode(new Node("Data Engineer",
                "Designs and builds data pipelines, ETL processes, and data warehouses. Ensures data flows reliably from source systems to analytics platforms. Works with tools like Apache Spark, Airflow, and SQL.",
                "Seme"));

        // L6 NO -> Mobile path
        // L7
        Node l7_mobile = new Node("Do you build native apps using platform-specific tools (Swift for iOS, Kotlin for Android)?");
        l6_frameworks.setNoNode(l7_mobile);

        // L7 YES/NO -> depth 8
        l7_mobile.setYesNode(new Node("Mobile Developer",
                "Creates mobile applications for iOS and Android. Builds smooth, touch-friendly interfaces with native performance. Works with Swift/SwiftUI or Kotlin/Jetpack Compose to deliver apps to millions of users.",
                "Uke"));
        l7_mobile.setNoNode(new Node("QA / Test Automation Engineer",
                "Ensures software quality by writing automated test scripts using tools like Selenium, Cypress, or Appium. Designs test plans, finds bugs before users do, and maintains CI test pipelines.",
                "Uke"));

        // L5 NO: non-web user-facing
        // L6
        Node l6_mobileAlt = new Node("Do you develop apps for smartphones and tablets?");
        l5_webStack.setNoNode(l6_mobileAlt);
        l6_mobileAlt.setYesNode(new Node("Mobile Developer",
                "Creates mobile applications for iOS and Android. Builds smooth, touch-friendly interfaces with native performance. Works with Swift/SwiftUI or Kotlin/Jetpack Compose to deliver apps to millions of users.",
                "Uke"));
        l6_mobileAlt.setNoNode(new Node("QA / Test Automation Engineer",
                "Ensures software quality by writing automated test scripts using tools like Selenium, Cypress, or Appium. Designs test plans, finds bugs before users do, and maintains CI test pipelines.",
                "Uke"));

        // L4 NO: Game Development
        // L5
        Node l5_games = new Node("Do you enjoy creating interactive 3D worlds, physics simulations, or multiplayer systems?");
        l4_notGames.setNoNode(l5_games);

        // L5 YES
        // L6
        Node l6_gameEngine = new Node("Do you work with game engines like Unity or Unreal Engine?");
        l5_games.setYesNode(l6_gameEngine);

        // L6 YES/NO -> depth 7
        l6_gameEngine.setYesNode(new Node("Game Developer",
                "Creates video games using Unity (C#) or Unreal Engine (C++). Builds game mechanics, physics systems, AI behaviors, and multiplayer networking. Brings virtual worlds and interactive stories to life.",
                "Seme"));
        l6_gameEngine.setNoNode(new Node("Game Developer",
                "Creates video games using Unity (C#) or Unreal Engine (C++). Builds game mechanics, physics systems, AI behaviors, and multiplayer networking. Brings virtual worlds and interactive stories to life.",
                "Seme"));

        // L5 NO: BI
        // L6
        Node l6_bi = new Node("Do you build dashboards and data visualizations for business decision-making?");
        l5_games.setNoNode(l6_bi);
        l6_bi.setYesNode(new Node("Business Intelligence (BI) Developer",
                "Creates interactive dashboards, reports, and data visualizations using tools like Power BI, Tableau, or Looker. Translates raw data into actionable insights that help executives make strategic decisions.",
                "Uke"));
        l6_bi.setNoNode(new Node("Business Intelligence (BI) Developer",
                "Creates interactive dashboards, reports, and data visualizations using tools like Power BI, Tableau, or Looker. Translates raw data into actionable insights that help executives make strategic decisions.",
                "Uke"));

        // L3 NO: Data-focused (2 careers)
        // L4
        Node l4_data = new Node("Do you build and train machine learning models or AI systems?");
        l3_apps.setNoNode(l4_data);

        // L4 YES
        // L5
        Node l5_ml = new Node("Do you focus on deploying models into production rather than pure research?");
        l4_data.setYesNode(l5_ml);

        // L5 YES
        // L6
        Node l6_mlprod = new Node("Do you optimize ML pipelines using frameworks like TensorFlow, PyTorch, or MLflow?");
        l5_ml.setYesNode(l6_mlprod);

        // L6 YES/NO -> depth 7
        l6_mlprod.setYesNode(new Node("AI / Machine Learning Engineer",
                "Builds, trains, and deploys machine learning models into production systems. Works with TensorFlow, PyTorch, and cloud ML services. Bridges the gap between data science research and real-world applications.",
                "Seme"));
        l6_mlprod.setNoNode(new Node("AI / Machine Learning Engineer",
                "Builds, trains, and deploys machine learning models into production systems. Works with TensorFlow, PyTorch, and cloud ML services. Bridges the gap between data science research and real-world applications.",
                "Seme"));

        // L5 NO: Data Scientist
        // L6
        Node l6_ds = new Node("Do you use Python, R, or SQL to analyze datasets and create statistical models?");
        l5_ml.setNoNode(l6_ds);
        l6_ds.setYesNode(new Node("Data Scientist",
                "Analyzes large datasets to discover patterns, build predictive models, and extract actionable insights. Uses Python, R, statistics, and visualization tools. Communicates findings to stakeholders through reports and presentations.",
                "Uke"));
        l6_ds.setNoNode(new Node("Data Scientist",
                "Analyzes large datasets to discover patterns, build predictive models, and extract actionable insights. Uses Python, R, statistics, and visualization tools. Communicates findings to stakeholders through reports and presentations.",
                "Uke"));

        // L4 NO: Data without ML
        // L5
        Node l5_dataNoML = new Node("Do you build data pipelines and warehouses rather than creating visualizations?");
        l4_data.setNoNode(l5_dataNoML);
        l5_dataNoML.setYesNode(new Node("Data Engineer",
                "Designs and builds data pipelines, ETL processes, and data warehouses. Ensures data flows reliably from source systems to analytics platforms. Works with tools like Apache Spark, Airflow, and SQL.",
                "Seme"));
        l5_dataNoML.setNoNode(new Node("Business Intelligence (BI) Developer",
                "Creates interactive dashboards, reports, and data visualizations using tools like Power BI, Tableau, or Looker. Translates raw data into actionable insights that help executives make strategic decisions.",
                "Uke"));

        // ------ L2 NO: Behind-the-scenes (5 careers, depth 7-10) ------
        // L3
        Node l3_devops = new Node("Do you focus on automating software delivery, deployments, and infrastructure as code?");
        l2_userFacing.setNoNode(l3_devops);

        // L3 YES: DevOps
        // L4
        Node l4_cicd = new Node("Do you work with CI/CD pipelines, Docker containers, and Kubernetes orchestration?");
        l3_devops.setYesNode(l4_cicd);

        // L4 YES
        // L5
        Node l5_devops = new Node("Do you manage cloud infrastructure using tools like Terraform, Ansible, or CloudFormation?");
        l4_cicd.setYesNode(l5_devops);

        l5_devops.setYesNode(new Node("DevOps Engineer",
                "Bridges development and operations teams. Automates build, test, and deployment pipelines using CI/CD tools, Docker, and Kubernetes. Manages infrastructure as code and ensures smooth, reliable software releases.",
                "Seme"));
        l5_devops.setNoNode(new Node("DevOps Engineer",
                "Bridges development and operations teams. Automates build, test, and deployment pipelines using CI/CD tools, Docker, and Kubernetes. Manages infrastructure as code and ensures smooth, reliable software releases.",
                "Seme"));

        // L4 NO
        // L5
        Node l5_backOrFull = new Node("Do you enjoy working on both the frontend and backend of applications?");
        l4_cicd.setNoNode(l5_backOrFull);
        l5_backOrFull.setYesNode(new Node("Full Stack Developer",
                "Handles both frontend interfaces AND backend servers. Comfortable with React/Vue on the client side and Node.js/Python/Java on the server side. A one-person army who can build complete web applications.",
                "Seme"));
        l5_backOrFull.setNoNode(new Node("Backend Developer",
                "Builds server-side applications, RESTful APIs, and microservices. Works with languages like Java, Python, Go, or Node.js. Handles authentication, business logic, and system integration.",
                "Seme"));

        // ================================================================
        // L1 NO — Infrastructure & Hardware (10 careers)
        // ================================================================
        // L2
        Node l2_digital = new Node("Do you work more with digital systems (servers, cloud, networks) than with physical hardware and circuits?");
        root.setNoNode(l2_digital);

        // ------ L2 YES: Digital Infrastructure (5 careers, depth 8-10) ------
        // L3
        Node l3_production = new Node("Do you manage and maintain live production systems that serve real users?");
        l2_digital.setYesNode(l3_production);

        // L3 YES: Production systems
        // L4
        Node l4_reliability = new Node("Do you focus specifically on system reliability, uptime monitoring, and incident response?");
        l3_production.setYesNode(l4_reliability);

        // L4 YES
        // L5
        Node l5_slo = new Node("Do you define and track Service Level Objectives (SLOs) and error budgets?");
        l4_reliability.setYesNode(l5_slo);

        // L5 YES
        // L6
        Node l6_auto = new Node("Do you write automation to reduce toil and prevent recurring outages?");
        l5_slo.setYesNode(l6_auto);

        // L6 YES
        // L7
        Node l7_observ = new Node("Do you build observability systems with metrics, logs, and distributed tracing?");
        l6_auto.setYesNode(l7_observ);

        l7_observ.setYesNode(new Node("Site Reliability Engineer (SRE)",
                "Keeps production systems running at 99.99% uptime. Combines software engineering with operations to build reliable, scalable systems. Defines SLOs, automates incident response, and eliminates manual toil.",
                "Seme"));
        l7_observ.setNoNode(new Node("Site Reliability Engineer (SRE)",
                "Keeps production systems running at 99.99% uptime. Combines software engineering with operations to build reliable, scalable systems. Defines SLOs, automates incident response, and eliminates manual toil.",
                "Seme"));

        // L6 NO
        l6_auto.setNoNode(new Node("System Engineer / Administrator",
                "Manages servers, operating systems, and user accounts. Installs and configures software, handles backups, monitors system health, and ensures the IT infrastructure runs smoothly for the organization.",
                "Seme"));

        // L5 NO
        l5_slo.setNoNode(new Node("System Engineer / Administrator",
                "Manages servers, operating systems, and user accounts. Installs and configures software, handles backups, monitors system health, and ensures the IT infrastructure runs smoothly for the organization.",
                "Seme"));

        // L4 NO: Not reliability-focused
        // L5
        Node l5_dataPipe = new Node("Do you design and manage data pipelines, ETL processes, and data storage systems?");
        l4_reliability.setNoNode(l5_dataPipe);

        // L5 YES
        l5_dataPipe.setYesNode(new Node("Data Engineer",
                "Designs and builds data pipelines, ETL processes, and data warehouses. Ensures data flows reliably from source systems to analytics platforms. Works with tools like Apache Spark, Airflow, and SQL.",
                "Seme"));

        // L5 NO: Cloud vs Network
        // L6
        Node l6_cloud = new Node("Do you specialize in cloud platforms like AWS, Azure, or Google Cloud?");
        l5_dataPipe.setNoNode(l6_cloud);

        // L6 YES
        // L7
        Node l7_cloudArch = new Node("Do you design cloud architectures and manage virtual infrastructure?");
        l6_cloud.setYesNode(l7_cloudArch);

        // L7 YES
        // L8
        Node l8_iac = new Node("Do you provision resources using Infrastructure as Code (Terraform, CloudFormation)?");
        l7_cloudArch.setYesNode(l8_iac);

        l8_iac.setYesNode(new Node("Cloud Engineer",
                "Manages cloud infrastructure on AWS, Azure, or GCP. Provisions virtual machines, networks, and storage. Uses Infrastructure as Code tools like Terraform to automate cloud resource management at scale.",
                "Uke"));
        l8_iac.setNoNode(new Node("Cloud Engineer",
                "Manages cloud infrastructure on AWS, Azure, or GCP. Provisions virtual machines, networks, and storage. Uses Infrastructure as Code tools like Terraform to automate cloud resource management at scale.",
                "Uke"));

        // L7 NO
        l7_cloudArch.setNoNode(new Node("Network Engineer",
                "Designs, implements, and maintains computer networks including LANs, WANs, and firewalls. Configures routers, switches, and VPNs. Ensures reliable, secure data communication across the organization.",
                "Uke"));

        // L6 NO: Traditional network
        // L7
        Node l7_netProto = new Node("Do you configure routers, switches, firewalls, and network protocols?");
        l6_cloud.setNoNode(l7_netProto);
        l7_netProto.setYesNode(new Node("Network Engineer",
                "Designs, implements, and maintains computer networks including LANs, WANs, and firewalls. Configures routers, switches, and VPNs. Ensures reliable, secure data communication across the organization.",
                "Uke"));
        l7_netProto.setNoNode(new Node("System Engineer / Administrator",
                "Manages servers, operating systems, and user accounts. Installs and configures software, handles backups, monitors system health, and ensures the IT infrastructure runs smoothly for the organization.",
                "Seme"));

        // L3 NO: Not production systems
        // L4
        Node l4_security = new Node("Do you work with cybersecurity, protecting systems from attacks and vulnerabilities?");
        l3_production.setNoNode(l4_security);

        // L4 YES
        // L5
        Node l5_pentest = new Node("Do you perform penetration testing and ethical hacking to find security flaws?");
        l4_security.setYesNode(l5_pentest);

        // L5 YES
        // L6
        Node l6_offensive = new Node("Do you write exploit code and simulate real-world attacks on systems?");
        l5_pentest.setYesNode(l6_offensive);

        // L6 YES
        // L7
        Node l7_redteam = new Node("Do you conduct red team operations and social engineering assessments?");
        l6_offensive.setYesNode(l7_redteam);

        l7_redteam.setYesNode(new Node("Cybersecurity Engineer / Pentester",
                "Protects organizations by finding and fixing security vulnerabilities. Performs penetration testing, code audits, and red team exercises. Works with tools like Burp Suite, Metasploit, and Wireshark.",
                "Seme"));
        l7_redteam.setNoNode(new Node("Cybersecurity Engineer / Pentester",
                "Protects organizations by finding and fixing security vulnerabilities. Performs penetration testing, code audits, and red team exercises. Works with tools like Burp Suite, Metasploit, and Wireshark.",
                "Seme"));

        // L6 NO
        l6_offensive.setNoNode(new Node("Blockchain Engineer",
                "Develops Smart Contracts and decentralized applications (DApps) on blockchain platforms like Ethereum and Solana. Works with cryptography, consensus algorithms, and Web3 technologies.",
                "Seme"));

        // L5 NO: Blockchain/crypto
        // L6
        Node l6_crypto = new Node("Do you work with blockchain technology, smart contracts, or decentralized systems?");
        l5_pentest.setNoNode(l6_crypto);
        l6_crypto.setYesNode(new Node("Blockchain Engineer",
                "Develops Smart Contracts and decentralized applications (DApps) on blockchain platforms like Ethereum and Solana. Works with cryptography, consensus algorithms, and Web3 technologies.",
                "Seme"));
        l6_crypto.setNoNode(new Node("Cybersecurity Engineer / Pentester",
                "Protects organizations by finding and fixing security vulnerabilities. Performs penetration testing, code audits, and red team exercises. Works with tools like Burp Suite, Metasploit, and Wireshark.",
                "Seme"));

        // ------ L2 NO: Physical/Hardware (5 careers, depth 7-10) ------
        // L3
        Node l3_iot = new Node("Does your work involve connecting physical devices to the internet or networks?");
        l2_digital.setNoNode(l3_iot);

        // L3 YES: IoT
        // L4
        Node l4_smart = new Node("Do you build smart systems that collect sensor data and automate physical environments?");
        l3_iot.setYesNode(l4_smart);

        // L4 YES
        // L5
        Node l5_iotProto = new Node("Do you work with IoT protocols like MQTT, CoAP, or Zigbee?");
        l4_smart.setYesNode(l5_iotProto);

        // L5 YES
        // L6
        Node l6_iotCloud = new Node("Do you integrate IoT devices with cloud platforms for data collection and monitoring?");
        l5_iotProto.setYesNode(l6_iotCloud);

        l6_iotCloud.setYesNode(new Node("IoT Engineer",
                "Connects physical devices to the Internet to create smart systems. Designs sensor networks, works with IoT protocols (MQTT, CoAP), and integrates devices with cloud platforms for monitoring and automation.",
                "Uke"));
        l6_iotCloud.setNoNode(new Node("IoT Engineer",
                "Connects physical devices to the Internet to create smart systems. Designs sensor networks, works with IoT protocols (MQTT, CoAP), and integrates devices with cloud platforms for monitoring and automation.",
                "Uke"));

        // L5 NO
        l5_iotProto.setNoNode(new Node("Embedded Systems Engineer",
                "Writes firmware and low-level software for microcontrollers (ARM, ESP32, STM32). Programs hardware at the register level, optimizes for memory and power constraints, and interfaces with sensors and actuators.",
                "Seme"));

        // L4 NO
        l4_smart.setNoNode(new Node("Embedded Systems Engineer",
                "Writes firmware and low-level software for microcontrollers (ARM, ESP32, STM32). Programs hardware at the register level, optimizes for memory and power constraints, and interfaces with sensors and actuators.",
                "Seme"));

        // L3 NO: Pure hardware
        // L4
        Node l4_circuits = new Node("Do you design electronic circuits, PCBs, or work with FPGAs?");
        l3_iot.setNoNode(l4_circuits);

        // L4 YES
        // L5
        Node l5_pcb = new Node("Do you use CAD tools like Altium, KiCad, or Vivado to design hardware?");
        l4_circuits.setYesNode(l5_pcb);

        // L5 YES
        // L6
        Node l6_chip = new Node("Do you design at the chip or FPGA level rather than board level?");
        l5_pcb.setYesNode(l6_chip);

        l6_chip.setYesNode(new Node("Hardware Design Engineer",
                "Designs electronic circuits, PCBs, FPGAs, and chipsets. Uses CAD tools like Altium Designer and Vivado. Creates schematics, runs simulations, and works with manufacturing to produce physical electronics.",
                "Seme"));
        l6_chip.setNoNode(new Node("Hardware Design Engineer",
                "Designs electronic circuits, PCBs, FPGAs, and chipsets. Uses CAD tools like Altium Designer and Vivado. Creates schematics, runs simulations, and works with manufacturing to produce physical electronics.",
                "Seme"));

        // L5 NO
        l5_pcb.setNoNode(new Node("Embedded Systems Engineer",
                "Writes firmware and low-level software for microcontrollers (ARM, ESP32, STM32). Programs hardware at the register level, optimizes for memory and power constraints, and interfaces with sensors and actuators.",
                "Seme"));

        // L4 NO: Not circuits either
        // L5
        Node l5_hwLast = new Node("Do you write low-level firmware for microcontrollers and embedded devices?");
        l4_circuits.setNoNode(l5_hwLast);
        l5_hwLast.setYesNode(new Node("Embedded Systems Engineer",
                "Writes firmware and low-level software for microcontrollers (ARM, ESP32, STM32). Programs hardware at the register level, optimizes for memory and power constraints, and interfaces with sensors and actuators.",
                "Seme"));
        l5_hwLast.setNoNode(new Node("Hardware Design Engineer",
                "Designs electronic circuits, PCBs, FPGAs, and chipsets. Uses CAD tools like Altium Designer and Vivado. Creates schematics, runs simulations, and works with manufacturing to produce physical electronics.",
                "Seme"));

        return new DecisionTree(root);
    }
}
