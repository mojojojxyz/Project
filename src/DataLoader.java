/**
 * Builds a Binary Decision Tree with 20 tech careers.
 * Redundant questions (where YES and NO both lead to same career) have been
 * removed and replaced directly with the leaf node at that position.
 *
 * After pruning, tree depth ranges from 4 to 8.
 */
public class DataLoader {

        /**
         * Creates a DecisionTree with 20 careers.
         *
         * @return a populated DecisionTree
         */
        public static DecisionTree loadInitialTree() {

                // ================================================================
                // L1 ROOT
                // ================================================================
                Node root = new Node(
                                "Do you prefer building software and writing code over managing infrastructure and hardware?");

                // ================================================================
                // L1 YES — Software & Development (10 careers)
                // ================================================================

                // L2
                Node l2_userFacing = new Node(
                                "Do you enjoy creating things that users directly see and interact with on screen?");
                root.setYesNode(l2_userFacing);

                // ------ L2 YES: User-facing (5 careers) ------

                // L3
                Node l3_apps = new Node(
                                "Are you more passionate about building interactive applications than analyzing data and reports?");
                l2_userFacing.setYesNode(l3_apps);

                // L3 YES: App Development
                // L4
                Node l4_notGames = new Node(
                                "Do you prefer developing real-world applications over creating video games?");
                l3_apps.setYesNode(l4_notGames);

                // L4 YES: Web/Mobile path
                // L5
                Node l5_webStack = new Node(
                                "Do you prefer working with web technologies (HTML, CSS, JavaScript) over native mobile platforms?");
                l4_notGames.setYesNode(l5_webStack);

                // L5 YES → web framework path
                // L6
                Node l6_frameworks = new Node(
                                "Do you enjoy building complex UIs with modern frameworks like React, Vue, or Angular?");
                l5_webStack.setYesNode(l6_frameworks);

                // L6 YES → frontend vs full stack
                // L7
                Node l7_clientOnly = new Node(
                                "Do you like to focus mainly on the browser side, leaving server-side logic to others?");
                l6_frameworks.setYesNode(l7_clientOnly);

                // L7 YES → Frontend Developer (depth 8)
                l7_clientOnly.setYesNode(new Node("Frontend Developer",
                                "Builds beautiful, responsive web interfaces using HTML, CSS, and JavaScript with modern frameworks like React or Vue. Focuses on user experience, accessibility, and performance in the browser.",
                                "Uke"));

                // L7 NO → Full Stack Developer (depth 8)
                l7_clientOnly.setNoNode(new Node("Full Stack Developer",
                                "Handles both frontend interfaces AND backend servers. Comfortable with React/Vue on the client side and Node.js/Python/Java on the server side. A one-person army who can build complete web applications.",
                                "Seme"));

                // L6 NO → Mobile Developer (depth 7)
                // (was: "Do you enjoy native tools?" YES/NO both → Mobile Dev — pruned)
                l6_frameworks.setNoNode(new Node("Mobile Developer",
                                "Creates mobile applications for iOS and Android. Builds smooth, touch-friendly interfaces with native performance. Works with Swift/SwiftUI or Kotlin/Jetpack Compose to deliver apps to millions of users.",
                                "Uke"));

                // L5 NO → non-web user-facing
                // L6
                Node l6_mobileOrQA = new Node("Do you like to develop apps for smartphones and tablets?");
                l5_webStack.setNoNode(l6_mobileOrQA);

                // L6 YES → Mobile Developer (depth 7)
                l6_mobileOrQA.setYesNode(new Node("Mobile Developer",
                                "Creates mobile applications for iOS and Android. Builds smooth, touch-friendly interfaces with native performance. Works with Swift/SwiftUI or Kotlin/Jetpack Compose to deliver apps to millions of users.",
                                "Uke"));

                // L6 NO → QA / Test Automation Engineer (depth 7)
                l6_mobileOrQA.setNoNode(new Node("QA / Test Automation Engineer",
                                "Ensures software quality by writing automated test scripts using tools like Selenium, Cypress, or Appium. Designs test plans, finds bugs before users do, and maintains CI test pipelines.",
                                "Uke"));

                // L4 NO: Game / BI path
                // L5
                Node l5_gamesOrBI = new Node(
                                "Do you like to create interactive 3D worlds, physics simulations, or multiplayer systems?");
                l4_notGames.setNoNode(l5_gamesOrBI);

                // L5 YES → Game Developer (depth 6)
                // (was: "Do you like Unity/Unreal?" YES/NO both → Game Dev — pruned)
                l5_gamesOrBI.setYesNode(new Node("Game Developer",
                                "Creates video games using Unity (C#) or Unreal Engine (C++). Builds game mechanics, physics systems, AI behaviors, and multiplayer networking. Brings virtual worlds and interactive stories to life.",
                                "Seme"));

                // L5 NO → BI Developer (depth 6)
                // (was: "Do you like dashboards?" YES/NO both → BI Dev — pruned)
                l5_gamesOrBI.setNoNode(new Node("Business Intelligence (BI) Developer",
                                "Creates interactive dashboards, reports, and data visualizations using tools like Power BI, Tableau, or Looker. Translates raw data into actionable insights that help executives make strategic decisions.",
                                "Uke"));

                // L3 NO: Data-focused path
                // L4
                Node l4_data = new Node("Do you like to build and train machine learning models or AI systems?");
                l3_apps.setNoNode(l4_data);

                // L4 YES → ML path
                // L5
                Node l5_ml = new Node(
                                "Do you like to focus on deploying models into production rather than pure research?");
                l4_data.setYesNode(l5_ml);

                // L5 YES → AI/ML Engineer (depth 6)
                // (was: "Do you use TensorFlow/PyTorch/MLflow?" YES/NO both → AI/ML — pruned)
                l5_ml.setYesNode(new Node("AI / Machine Learning Engineer",
                                "Builds, trains, and deploys machine learning models into production systems. Works with TensorFlow, PyTorch, and cloud ML services. Bridges the gap between data science research and real-world applications.",
                                "Seme"));

                // L5 NO → Data Scientist (depth 6)
                // (was: "Do you use Python/R/SQL?" YES/NO both → Data Scientist — pruned)
                l5_ml.setNoNode(new Node("Data Scientist",
                                "Analyzes large datasets to discover patterns, build predictive models, and extract actionable insights. Uses Python, R, statistics, and visualization tools. Communicates findings to stakeholders through reports and presentations.",
                                "Uke"));

                // L4 NO: Data without ML
                // L5
                Node l5_dataNoML = new Node(
                                "Do you prefer to build data pipelines and warehouses rather than creating visualizations?");
                l4_data.setNoNode(l5_dataNoML);

                // L5 YES → Data Engineer (depth 6)
                l5_dataNoML.setYesNode(new Node("Data Engineer",
                                "Designs and builds data pipelines, ETL processes, and data warehouses. Ensures data flows reliably from source systems to analytics platforms. Works with tools like Apache Spark, Airflow, and SQL.",
                                "Seme"));

                // L5 NO → BI Developer (depth 6)
                l5_dataNoML.setNoNode(new Node("Business Intelligence (BI) Developer",
                                "Creates interactive dashboards, reports, and data visualizations using tools like Power BI, Tableau, or Looker. Translates raw data into actionable insights that help executives make strategic decisions.",
                                "Uke"));

                // ------ L2 NO: Behind-the-scenes (5 careers) ------

                // L3
                Node l3_devops = new Node(
                                "Do you like to focus on automating software delivery, deployments, and infrastructure as code?");
                l2_userFacing.setNoNode(l3_devops);

                // L3 YES → DevOps path
                // L4
                Node l4_cicd = new Node(
                                "Do you enjoy working with CI/CD pipelines, Docker containers, and Kubernetes orchestration?");
                l3_devops.setYesNode(l4_cicd);

                // L4 YES → DevOps Engineer (depth 5)
                // (was: "Do you use Terraform/Ansible?" YES/NO both → DevOps — pruned)
                l4_cicd.setYesNode(new Node("DevOps Engineer",
                                "Bridges development and operations teams. Automates build, test, and deployment pipelines using CI/CD tools, Docker, and Kubernetes. Manages infrastructure as code and ensures smooth, reliable software releases.",
                                "Seme"));

                // L4 NO → Full Stack / Backend path
                // L5
                Node l5_backOrFull = new Node("Do you like working on both the frontend and backend of applications?");
                l4_cicd.setNoNode(l5_backOrFull);

                // L5 YES → Full Stack Developer (depth 6)
                l5_backOrFull.setYesNode(new Node("Full Stack Developer",
                                "Handles both frontend interfaces AND backend servers. Comfortable with React/Vue on the client side and Node.js/Python/Java on the server side. A one-person army who can build complete web applications.",
                                "Seme"));

                // L5 NO → Backend Developer (depth 6)
                l5_backOrFull.setNoNode(new Node("Backend Developer",
                                "Builds server-side applications, RESTful APIs, and microservices. Works with languages like Java, Python, Go, or Node.js. Handles authentication, business logic, and system integration.",
                                "Seme"));

                // L3 NO: Not DevOps → Backend/Full Stack path
                // L4
                Node l4_backendPath = new Node(
                                "Do you like working on both the frontend and backend of applications?");
                l3_devops.setNoNode(l4_backendPath);

                // L4 YES → Full Stack Developer (depth 5)
                l4_backendPath.setYesNode(new Node("Full Stack Developer",
                                "Handles both frontend interfaces AND backend servers. Comfortable with React/Vue on the client side and Node.js/Python/Java on the server side. A one-person army who can build complete web applications.",
                                "Seme"));

                // L4 NO → Backend Developer (depth 5)
                l4_backendPath.setNoNode(new Node("Backend Developer",
                                "Builds server-side applications, RESTful APIs, and microservices. Works with languages like Java, Python, Go, or Node.js. Handles authentication, business logic, and system integration.",
                                "Seme"));

                // ================================================================
                // L1 NO — Infrastructure & Hardware (10 careers)
                // ================================================================

                // L2
                Node l2_digital = new Node(
                                "Do you like to work more with digital systems (servers, cloud, networks) than with physical hardware and circuits?");
                root.setNoNode(l2_digital);

                // ------ L2 YES: Digital Infrastructure (5 careers) ------

                // L3
                Node l3_production = new Node(
                                "Do you like to manage and maintain live production systems that serve real users?");
                l2_digital.setYesNode(l3_production);

                // L3 YES → Production systems path
                // L4
                Node l4_reliability = new Node(
                                "Are you interested in system reliability, uptime monitoring, and incident response?");
                l3_production.setYesNode(l4_reliability);

                // L4 YES → SRE path
                // L5
                Node l5_slo = new Node(
                                "Do you like to define and track Service Level Objectives (SLOs) and error budgets?");
                l4_reliability.setYesNode(l5_slo);

                // L5 YES → SRE (depth 6)
                // (was: "Do you like automation?" YES → "Do you like observability?" YES/NO →
                // SRE — both pruned)
                l5_slo.setYesNode(new Node("Site Reliability Engineer (SRE)",
                                "Keeps production systems running at 99.99% uptime. Combines software engineering with operations to build reliable, scalable systems. Defines SLOs, automates incident response, and eliminates manual toil.",
                                "Seme"));

                // L5 NO → System Engineer (depth 6)
                l5_slo.setNoNode(new Node("System Engineer / Administrator",
                                "Manages servers, operating systems, and user accounts. Installs and configures software, handles backups, monitors system health, and ensures the IT infrastructure runs smoothly for the organization.",
                                "Seme"));

                // L4 NO → Data / Cloud / Network path
                // L5
                Node l5_dataPipe = new Node(
                                "Do you fancy to design and manage data pipelines, ETL processes, and data storage systems?");
                l4_reliability.setNoNode(l5_dataPipe);

                // L5 YES → Data Engineer (depth 6)
                l5_dataPipe.setYesNode(new Node("Data Engineer",
                                "Designs and builds data pipelines, ETL processes, and data warehouses. Ensures data flows reliably from source systems to analytics platforms. Works with tools like Apache Spark, Airflow, and SQL.",
                                "Seme"));

                // L5 NO → Cloud / Network path
                // L6
                Node l6_cloud = new Node("Are you interested in cloud platforms like AWS, Azure, or Google Cloud?");
                l5_dataPipe.setNoNode(l6_cloud);

                // L6 YES → Cloud path
                // L7
                Node l7_cloudArch = new Node(
                                "Do you enjoy designing cloud architectures and managing virtual infrastructure?");
                l6_cloud.setYesNode(l7_cloudArch);

                // L7 YES → Cloud Engineer (depth 8)
                // (was: "Do you use IaC?" YES/NO → Cloud Eng — pruned)
                l7_cloudArch.setYesNode(new Node("Cloud Engineer",
                                "Manages cloud infrastructure on AWS, Azure, or GCP. Provisions virtual machines, networks, and storage. Uses Infrastructure as Code tools like Terraform to automate cloud resource management at scale.",
                                "Uke"));

                // L7 NO → Network Engineer (depth 8)
                l7_cloudArch.setNoNode(new Node("Network Engineer",
                                "Designs, implements, and maintains computer networks including LANs, WANs, and firewalls. Configures routers, switches, and VPNs. Ensures reliable, secure data communication across the organization.",
                                "Uke"));

                // L6 NO → Traditional network path
                // L7
                Node l7_netProto = new Node(
                                "Do you enjoy configuring routers, switches, firewalls, and network protocols?");
                l6_cloud.setNoNode(l7_netProto);

                // L7 YES → Network Engineer (depth 8)
                l7_netProto.setYesNode(new Node("Network Engineer",
                                "Designs, implements, and maintains computer networks including LANs, WANs, and firewalls. Configures routers, switches, and VPNs. Ensures reliable, secure data communication across the organization.",
                                "Uke"));

                // L7 NO → System Engineer (depth 8)
                l7_netProto.setNoNode(new Node("System Engineer / Administrator",
                                "Manages servers, operating systems, and user accounts. Installs and configures software, handles backups, monitors system health, and ensures the IT infrastructure runs smoothly for the organization.",
                                "Seme"));

                // L3 NO: Not production systems → Security / Cloud / Network
                // L4
                Node l4_security = new Node(
                                "Do you like to work with cybersecurity, protecting systems from attacks and vulnerabilities?");
                l3_production.setNoNode(l4_security);

                // L4 YES → Pentest path
                // L5
                Node l5_pentest = new Node(
                                "Do you enjoy performing penetration testing and ethical hacking to find security flaws?");
                l4_security.setYesNode(l5_pentest);

                // L5 YES → Offensive/Red Team path
                // L6
                Node l6_offensive = new Node(
                                "Do you like to write exploit code and simulate real-world attacks on systems?");
                l5_pentest.setYesNode(l6_offensive);

                // L6 YES → Cybersecurity Engineer (depth 7)
                // (was: "Do you like red team/social eng?" YES/NO both → Cybersec — pruned)
                l6_offensive.setYesNode(new Node("Cybersecurity Engineer / Pentester",
                                "Protects organizations by finding and fixing security vulnerabilities. Performs penetration testing, code audits, and red team exercises. Works with tools like Burp Suite, Metasploit, and Wireshark.",
                                "Seme"));

                // L6 NO → Blockchain Engineer (depth 7)
                l6_offensive.setNoNode(new Node("Blockchain Engineer",
                                "Develops Smart Contracts and decentralized applications (DApps) on blockchain platforms like Ethereum and Solana. Works with cryptography, consensus algorithms, and Web3 technologies.",
                                "Seme"));

                // L5 NO → Blockchain / Cybersec fallback
                // L6
                Node l6_crypto = new Node(
                                "Do you enjoy working with blockchain technology, smart contracts, or decentralized systems?");
                l5_pentest.setNoNode(l6_crypto);

                // L6 YES → Blockchain Engineer (depth 7)
                l6_crypto.setYesNode(new Node("Blockchain Engineer",
                                "Develops Smart Contracts and decentralized applications (DApps) on blockchain platforms like Ethereum and Solana. Works with cryptography, consensus algorithms, and Web3 technologies.",
                                "Seme"));

                // L6 NO → Cybersecurity Engineer (depth 7)
                l6_crypto.setNoNode(new Node("Cybersecurity Engineer / Pentester",
                                "Protects organizations by finding and fixing security vulnerabilities. Performs penetration testing, code audits, and red team exercises. Works with tools like Burp Suite, Metasploit, and Wireshark.",
                                "Seme"));

                // L4 NO → Cloud / Network fallback
                // L5
                Node l5_cloudOrNet = new Node(
                                "Are you interested in cloud platforms like AWS, Azure, or Google Cloud?");
                l4_security.setNoNode(l5_cloudOrNet);

                // L5 YES → Cloud Engineer (depth 6)
                l5_cloudOrNet.setYesNode(new Node("Cloud Engineer",
                                "Manages cloud infrastructure on AWS, Azure, or GCP. Provisions virtual machines, networks, and storage. Uses Infrastructure as Code tools like Terraform to automate cloud resource management at scale.",
                                "Uke"));

                // L5 NO → Network Engineer (depth 6)
                l5_cloudOrNet.setNoNode(new Node("Network Engineer",
                                "Designs, implements, and maintains computer networks including LANs, WANs, and firewalls. Configures routers, switches, and VPNs. Ensures reliable, secure data communication across the organization.",
                                "Uke"));

                // ------ L2 NO: Physical/Hardware (5 careers) ------

                // L3
                Node l3_iot = new Node(
                                "Do you like working with connecting physical devices to the internet or networks?");
                l2_digital.setNoNode(l3_iot);

                // L3 YES → IoT path
                // L4
                Node l4_smart = new Node(
                                "Do you like to build smart systems that collect sensor data and automate physical environments?");
                l3_iot.setYesNode(l4_smart);

                // L4 YES → IoT Protocol path
                // L5
                Node l5_iotProto = new Node("Do you enjoy working with IoT protocols like MQTT, CoAP, or Zigbee?");
                l4_smart.setYesNode(l5_iotProto);

                // L5 YES → IoT Engineer (depth 6)
                // (was: "Do you integrate with cloud?" YES/NO both → IoT Eng — pruned)
                l5_iotProto.setYesNode(new Node("IoT Engineer",
                                "Connects physical devices to the Internet to create smart systems. Designs sensor networks, works with IoT protocols (MQTT, CoAP), and integrates devices with cloud platforms for monitoring and automation.",
                                "Uke"));

                // L5 NO → Embedded Systems Engineer (depth 6)
                l5_iotProto.setNoNode(new Node("Embedded Systems Engineer",
                                "Writes firmware and low-level software for microcontrollers (ARM, ESP32, STM32). Programs hardware at the register level, optimizes for memory and power constraints, and interfaces with sensors and actuators.",
                                "Seme"));

                // L4 NO → Embedded Systems Engineer (depth 5)
                l4_smart.setNoNode(new Node("Embedded Systems Engineer",
                                "Writes firmware and low-level software for microcontrollers (ARM, ESP32, STM32). Programs hardware at the register level, optimizes for memory and power constraints, and interfaces with sensors and actuators.",
                                "Seme"));

                // L3 NO: Pure hardware path
                // L4
                Node l4_circuits = new Node("Do you like to design electronic circuits, PCBs, or work with FPGAs?");
                l3_iot.setNoNode(l4_circuits);

                // L4 YES → Hardware path
                // L5
                Node l5_pcb = new Node(
                                "Do you enjoy using CAD tools like Altium, KiCad, or Vivado to design hardware?");
                l4_circuits.setYesNode(l5_pcb);

                // L5 YES → Hardware Design Engineer (depth 6)
                // (was: "Do you design at chip/FPGA level?" YES/NO both → Hardware — pruned)
                l5_pcb.setYesNode(new Node("Hardware Design Engineer",
                                "Designs electronic circuits, PCBs, FPGAs, and chipsets. Uses CAD tools like Altium Designer and Vivado. Creates schematics, runs simulations, and works with manufacturing to produce physical electronics.",
                                "Seme"));

                // L5 NO → Embedded Systems Engineer (depth 6)
                l5_pcb.setNoNode(new Node("Embedded Systems Engineer",
                                "Writes firmware and low-level software for microcontrollers (ARM, ESP32, STM32). Programs hardware at the register level, optimizes for memory and power constraints, and interfaces with sensors and actuators.",
                                "Seme"));

                // L4 NO: Not circuits
                // L5
                Node l5_hwLast = new Node(
                                "Do you like to write low-level firmware for microcontrollers and embedded devices?");
                l4_circuits.setNoNode(l5_hwLast);

                // L5 YES → Embedded Systems Engineer (depth 6)
                l5_hwLast.setYesNode(new Node("Embedded Systems Engineer",
                                "Writes firmware and low-level software for microcontrollers (ARM, ESP32, STM32). Programs hardware at the register level, optimizes for memory and power constraints, and interfaces with sensors and actuators.",
                                "Seme"));

                // L5 NO → Hardware Design Engineer (depth 6)
                l5_hwLast.setNoNode(new Node("Hardware Design Engineer",
                                "Designs electronic circuits, PCBs, FPGAs, and chipsets. Uses CAD tools like Altium Designer and Vivado. Creates schematics, runs simulations, and works with manufacturing to produce physical electronics.",
                                "Seme"));

                return new DecisionTree(root);
        }
}