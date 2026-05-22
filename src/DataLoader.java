/**
 * สร้าง Binary Decision Tree ที่มีอาชีพด้านเทค 20 อาชีพ
 * คำถามซ้ำซ้อน (ที่ตอบ YES และ NO ไปสู่อาชีพเดียวกัน) ถูกตัดออก
 * และแทนที่ด้วยโหนดใบโดยตรงในตำแหน่งนั้น
 *
 * หลังจากตัดแล้ว ความลึกของต้นไม้อยู่ระหว่าง 4 ถึง 8
 */
public class DataLoader {

        /**
         * สร้าง DecisionTree ที่มี 20 อาชีพ
         *
         * @return DecisionTree ที่บรรจุข้อมูลแล้ว
         */
        public static DecisionTree loadInitialTree() {

                // ================================================================
                // ระดับ 1 — โหนดราก (ROOT)
                // ================================================================
                Node root = new Node(
                                "Do you prefer building software and writing code over managing infrastructure and hardware?");

                // ================================================================
                // ระดับ 1 ตอบ YES — กลุ่มซอฟต์แวร์และพัฒนา (10 อาชีพ)
                // ================================================================

                // ระดับ 2
                Node l2_userFacing = new Node(
                                "Do you enjoy creating things that users directly see and interact with on screen?");
                root.setYesNode(l2_userFacing);

                // ------ ระดับ 2 ตอบ YES: งานที่ผู้ใช้เห็นโดยตรง (5 อาชีพ) ------

                // ระดับ 3
                Node l3_apps = new Node(
                                "Are you more passionate about building interactive applications than analyzing data and reports?");
                l2_userFacing.setYesNode(l3_apps);

                // ระดับ 3 ตอบ YES: สายพัฒนาแอป
                // ระดับ 4
                Node l4_notGames = new Node(
                                "Do you prefer developing real-world applications over creating video games?");
                l3_apps.setYesNode(l4_notGames);

                // ระดับ 4 ตอบ YES: สาย Web/Mobile
                // ระดับ 5
                Node l5_webStack = new Node(
                                "Do you prefer working with web technologies (HTML, CSS, JavaScript) over native mobile platforms?");
                l4_notGames.setYesNode(l5_webStack);

                // ระดับ 5 ตอบ YES → สายเว็บเฟรมเวิร์ก
                // ระดับ 6
                Node l6_frameworks = new Node(
                                "Do you enjoy building complex UIs with modern frameworks like React, Vue, or Angular?");
                l5_webStack.setYesNode(l6_frameworks);

                // ระดับ 6 ตอบ YES → frontend vs full stack
                // ระดับ 7
                Node l7_clientOnly = new Node(
                                "Do you like to focus mainly on the browser side, leaving server-side logic to others?");
                l6_frameworks.setYesNode(l7_clientOnly);

                // ระดับ 7 ตอบ YES → Frontend Developer (ความลึก 8)
                l7_clientOnly.setYesNode(new Node("Frontend Developer",
                                "Builds beautiful, responsive web interfaces using HTML, CSS, and JavaScript with modern frameworks like React or Vue. Focuses on user experience, accessibility, and performance in the browser.",
                                "Uke"));

                // ระดับ 7 ตอบ NO → Full Stack Developer (ความลึก 8)
                l7_clientOnly.setNoNode(new Node("Full Stack Developer",
                                "Handles both frontend interfaces AND backend servers. Comfortable with React/Vue on the client side and Node.js/Python/Java on the server side. A one-person army who can build complete web applications.",
                                "Seme"));

                // ระดับ 6 ตอบ NO → Mobile Developer (ความลึก 7)
                // (เดิม: "Do you enjoy native tools?" ตอบ YES/NO ไปที่ Mobile Dev ทั้งคู่ — ตัดออก)
                l6_frameworks.setNoNode(new Node("Mobile Developer",
                                "Creates mobile applications for iOS and Android. Builds smooth, touch-friendly interfaces with native performance. Works with Swift/SwiftUI or Kotlin/Jetpack Compose to deliver apps to millions of users.",
                                "Uke"));

                // ระดับ 5 ตอบ NO → งานที่ไม่ใช่เว็บแต่ผู้ใช้เห็น
                // ระดับ 6
                Node l6_mobileOrQA = new Node("Do you like to develop apps for smartphones and tablets?");
                l5_webStack.setNoNode(l6_mobileOrQA);

                // ระดับ 6 ตอบ YES → Mobile Developer (ความลึก 7)
                l6_mobileOrQA.setYesNode(new Node("Mobile Developer",
                                "Creates mobile applications for iOS and Android. Builds smooth, touch-friendly interfaces with native performance. Works with Swift/SwiftUI or Kotlin/Jetpack Compose to deliver apps to millions of users.",
                                "Uke"));

                // ระดับ 6 ตอบ NO → QA / Test Automation Engineer (ความลึก 7)
                l6_mobileOrQA.setNoNode(new Node("QA / Test Automation Engineer",
                                "Ensures software quality by writing automated test scripts using tools like Selenium, Cypress, or Appium. Designs test plans, finds bugs before users do, and maintains CI test pipelines.",
                                "Uke"));

                // ระดับ 4 ตอบ NO: สาย Game / BI
                // ระดับ 5
                Node l5_gamesOrBI = new Node(
                                "Do you like to create interactive 3D worlds, physics simulations, or multiplayer systems?");
                l4_notGames.setNoNode(l5_gamesOrBI);

                // ระดับ 5 ตอบ YES → Game Developer (ความลึก 6)
                // (เดิม: "Do you like Unity/Unreal?" ตอบ YES/NO ไปที่ Game Dev ทั้งคู่ — ตัดออก)
                l5_gamesOrBI.setYesNode(new Node("Game Developer",
                                "Creates video games using Unity (C#) or Unreal Engine (C++). Builds game mechanics, physics systems, AI behaviors, and multiplayer networking. Brings virtual worlds and interactive stories to life.",
                                "Seme"));

                // ระดับ 5 ตอบ NO → BI Developer (ความลึก 6)
                // (เดิม: "Do you like dashboards?" ตอบ YES/NO ไปที่ BI Dev ทั้งคู่ — ตัดออก)
                l5_gamesOrBI.setNoNode(new Node("Business Intelligence (BI) Developer",
                                "Creates interactive dashboards, reports, and data visualizations using tools like Power BI, Tableau, or Looker. Translates raw data into actionable insights that help executives make strategic decisions.",
                                "Uke"));

                // ระดับ 3 ตอบ NO: สายข้อมูล (Data)
                // ระดับ 4
                Node l4_data = new Node("Do you like to build and train machine learning models or AI systems?");
                l3_apps.setNoNode(l4_data);

                // ระดับ 4 ตอบ YES → สาย ML
                // ระดับ 5
                Node l5_ml = new Node(
                                "Do you like to focus on deploying models into production rather than pure research?");
                l4_data.setYesNode(l5_ml);

                // ระดับ 5 ตอบ YES → AI/ML Engineer (ความลึก 6)
                // (เดิม: "Do you use TensorFlow/PyTorch/MLflow?" ตอบ YES/NO ไปที่ AI/ML ทั้งคู่ — ตัดออก)
                l5_ml.setYesNode(new Node("AI / Machine Learning Engineer",
                                "Builds, trains, and deploys machine learning models into production systems. Works with TensorFlow, PyTorch, and cloud ML services. Bridges the gap between data science research and real-world applications.",
                                "Seme"));

                // ระดับ 5 ตอบ NO → Data Scientist (ความลึก 6)
                // (เดิม: "Do you use Python/R/SQL?" ตอบ YES/NO ไปที่ Data Scientist ทั้งคู่ — ตัดออก)
                l5_ml.setNoNode(new Node("Data Scientist",
                                "Analyzes large datasets to discover patterns, build predictive models, and extract actionable insights. Uses Python, R, statistics, and visualization tools. Communicates findings to stakeholders through reports and presentations.",
                                "Uke"));

                // ระดับ 4 ตอบ NO: สายข้อมูลที่ไม่ใช่ ML
                // ระดับ 5
                Node l5_dataNoML = new Node(
                                "Do you prefer to build data pipelines and warehouses rather than creating visualizations?");
                l4_data.setNoNode(l5_dataNoML);

                // ระดับ 5 ตอบ YES → Data Engineer (ความลึก 6)
                l5_dataNoML.setYesNode(new Node("Data Engineer",
                                "Designs and builds data pipelines, ETL processes, and data warehouses. Ensures data flows reliably from source systems to analytics platforms. Works with tools like Apache Spark, Airflow, and SQL.",
                                "Seme"));

                // ระดับ 5 ตอบ NO → BI Developer (ความลึก 6)
                l5_dataNoML.setNoNode(new Node("Business Intelligence (BI) Developer",
                                "Creates interactive dashboards, reports, and data visualizations using tools like Power BI, Tableau, or Looker. Translates raw data into actionable insights that help executives make strategic decisions.",
                                "Uke"));

                // ------ ระดับ 2 ตอบ NO: งานเบื้องหลัง (5 อาชีพ) ------

                // ระดับ 3
                Node l3_devops = new Node(
                                "Do you like to focus on automating software delivery, deployments, and infrastructure as code?");
                l2_userFacing.setNoNode(l3_devops);

                // ระดับ 3 ตอบ YES → สาย DevOps
                // ระดับ 4
                Node l4_cicd = new Node(
                                "Do you enjoy working with CI/CD pipelines, Docker containers, and Kubernetes orchestration?");
                l3_devops.setYesNode(l4_cicd);

                // ระดับ 4 ตอบ YES → DevOps Engineer (ความลึก 5)
                // (เดิม: "Do you use Terraform/Ansible?" ตอบ YES/NO ไปที่ DevOps ทั้งคู่ — ตัดออก)
                l4_cicd.setYesNode(new Node("DevOps Engineer",
                                "Bridges development and operations teams. Automates build, test, and deployment pipelines using CI/CD tools, Docker, and Kubernetes. Manages infrastructure as code and ensures smooth, reliable software releases.",
                                "Seme"));

                // ระดับ 4 ตอบ NO → สาย Full Stack / Backend
                // ระดับ 5
                Node l5_backOrFull = new Node("Do you like working on both the frontend and backend of applications?");
                l4_cicd.setNoNode(l5_backOrFull);

                // ระดับ 5 ตอบ YES → Full Stack Developer (ความลึก 6)
                l5_backOrFull.setYesNode(new Node("Full Stack Developer",
                                "Handles both frontend interfaces AND backend servers. Comfortable with React/Vue on the client side and Node.js/Python/Java on the server side. A one-person army who can build complete web applications.",
                                "Seme"));

                // ระดับ 5 ตอบ NO → Backend Developer (ความลึก 6)
                l5_backOrFull.setNoNode(new Node("Backend Developer",
                                "Builds server-side applications, RESTful APIs, and microservices. Works with languages like Java, Python, Go, or Node.js. Handles authentication, business logic, and system integration.",
                                "Seme"));

                // ระดับ 3 ตอบ NO: ไม่ใช่ DevOps → สาย Backend/Full Stack
                // ระดับ 4
                Node l4_backendPath = new Node(
                                "Do you like working on both the frontend and backend of applications?");
                l3_devops.setNoNode(l4_backendPath);

                // ระดับ 4 ตอบ YES → Full Stack Developer (ความลึก 5)
                l4_backendPath.setYesNode(new Node("Full Stack Developer",
                                "Handles both frontend interfaces AND backend servers. Comfortable with React/Vue on the client side and Node.js/Python/Java on the server side. A one-person army who can build complete web applications.",
                                "Seme"));

                // ระดับ 4 ตอบ NO → Backend Developer (ความลึก 5)
                l4_backendPath.setNoNode(new Node("Backend Developer",
                                "Builds server-side applications, RESTful APIs, and microservices. Works with languages like Java, Python, Go, or Node.js. Handles authentication, business logic, and system integration.",
                                "Seme"));

                // ================================================================
                // ระดับ 1 ตอบ NO — กลุ่มโครงสร้างพื้นฐานและฮาร์ดแวร์ (10 อาชีพ)
                // ================================================================

                // ระดับ 2
                Node l2_digital = new Node(
                                "Do you like to work more with digital systems (servers, cloud, networks) than with physical hardware and circuits?");
                root.setNoNode(l2_digital);

                // ------ ระดับ 2 ตอบ YES: โครงสร้างพื้นฐานดิจิทัล (5 อาชีพ) ------

                // ระดับ 3
                Node l3_production = new Node(
                                "Do you like to manage and maintain live production systems that serve real users?");
                l2_digital.setYesNode(l3_production);

                // ระดับ 3 ตอบ YES → สายระบบ Production
                // ระดับ 4
                Node l4_reliability = new Node(
                                "Are you interested in system reliability, uptime monitoring, and incident response?");
                l3_production.setYesNode(l4_reliability);

                // ระดับ 4 ตอบ YES → สาย SRE
                // ระดับ 5
                Node l5_slo = new Node(
                                "Do you like to define and track Service Level Objectives (SLOs) and error budgets?");
                l4_reliability.setYesNode(l5_slo);

                // ระดับ 5 ตอบ YES → SRE (ความลึก 6)
                // (เดิม: "Do you like automation?" ตอบ YES → "Do you like observability?" ตอบ YES/NO →
                // SRE — ตัดออกทั้งคู่)
                l5_slo.setYesNode(new Node("Site Reliability Engineer (SRE)",
                                "Keeps production systems running at 99.99% uptime. Combines software engineering with operations to build reliable, scalable systems. Defines SLOs, automates incident response, and eliminates manual toil.",
                                "Seme"));

                // ระดับ 5 ตอบ NO → System Engineer (ความลึก 6)
                l5_slo.setNoNode(new Node("System Engineer / Administrator",
                                "Manages servers, operating systems, and user accounts. Installs and configures software, handles backups, monitors system health, and ensures the IT infrastructure runs smoothly for the organization.",
                                "Seme"));

                // ระดับ 4 ตอบ NO → สาย Data / Cloud / Network
                // ระดับ 5
                Node l5_dataPipe = new Node(
                                "Do you fancy to design and manage data pipelines, ETL processes, and data storage systems?");
                l4_reliability.setNoNode(l5_dataPipe);

                // ระดับ 5 ตอบ YES → Data Engineer (ความลึก 6)
                l5_dataPipe.setYesNode(new Node("Data Engineer",
                                "Designs and builds data pipelines, ETL processes, and data warehouses. Ensures data flows reliably from source systems to analytics platforms. Works with tools like Apache Spark, Airflow, and SQL.",
                                "Seme"));

                // ระดับ 5 ตอบ NO → สาย Cloud / Network
                // ระดับ 6
                Node l6_cloud = new Node("Are you interested in cloud platforms like AWS, Azure, or Google Cloud?");
                l5_dataPipe.setNoNode(l6_cloud);

                // ระดับ 6 ตอบ YES → สาย Cloud
                // ระดับ 7
                Node l7_cloudArch = new Node(
                                "Do you enjoy designing cloud architectures and managing virtual infrastructure?");
                l6_cloud.setYesNode(l7_cloudArch);

                // ระดับ 7 ตอบ YES → Cloud Engineer (ความลึก 8)
                // (เดิม: "Do you use IaC?" ตอบ YES/NO ไปที่ Cloud Eng ทั้งคู่ — ตัดออก)
                l7_cloudArch.setYesNode(new Node("Cloud Engineer",
                                "Manages cloud infrastructure on AWS, Azure, or GCP. Provisions virtual machines, networks, and storage. Uses Infrastructure as Code tools like Terraform to automate cloud resource management at scale.",
                                "Uke"));

                // ระดับ 7 ตอบ NO → Network Engineer (ความลึก 8)
                l7_cloudArch.setNoNode(new Node("Network Engineer",
                                "Designs, implements, and maintains computer networks including LANs, WANs, and firewalls. Configures routers, switches, and VPNs. Ensures reliable, secure data communication across the organization.",
                                "Uke"));

                // ระดับ 6 ตอบ NO → สายเครือข่ายแบบดั้งเดิม
                // ระดับ 7
                Node l7_netProto = new Node(
                                "Do you enjoy configuring routers, switches, firewalls, and network protocols?");
                l6_cloud.setNoNode(l7_netProto);

                // ระดับ 7 ตอบ YES → Network Engineer (ความลึก 8)
                l7_netProto.setYesNode(new Node("Network Engineer",
                                "Designs, implements, and maintains computer networks including LANs, WANs, and firewalls. Configures routers, switches, and VPNs. Ensures reliable, secure data communication across the organization.",
                                "Uke"));

                // ระดับ 7 ตอบ NO → System Engineer (ความลึก 8)
                l7_netProto.setNoNode(new Node("System Engineer / Administrator",
                                "Manages servers, operating systems, and user accounts. Installs and configures software, handles backups, monitors system health, and ensures the IT infrastructure runs smoothly for the organization.",
                                "Seme"));

                // ระดับ 3 ตอบ NO: ไม่ใช่ระบบ Production → สาย Security / Cloud / Network
                // ระดับ 4
                Node l4_security = new Node(
                                "Do you like to work with cybersecurity, protecting systems from attacks and vulnerabilities?");
                l3_production.setNoNode(l4_security);

                // ระดับ 4 ตอบ YES → สาย Pentest
                // ระดับ 5
                Node l5_pentest = new Node(
                                "Do you enjoy performing penetration testing and ethical hacking to find security flaws?");
                l4_security.setYesNode(l5_pentest);

                // ระดับ 5 ตอบ YES → สาย Offensive/Red Team
                // ระดับ 6
                Node l6_offensive = new Node(
                                "Do you like to write exploit code and simulate real-world attacks on systems?");
                l5_pentest.setYesNode(l6_offensive);

                // ระดับ 6 ตอบ YES → Cybersecurity Engineer (ความลึก 7)
                // (เดิม: "Do you like red team/social eng?" ตอบ YES/NO ไปที่ Cybersec ทั้งคู่ — ตัดออก)
                l6_offensive.setYesNode(new Node("Cybersecurity Engineer / Pentester",
                                "Protects organizations by finding and fixing security vulnerabilities. Performs penetration testing, code audits, and red team exercises. Works with tools like Burp Suite, Metasploit, and Wireshark.",
                                "Seme"));

                // ระดับ 6 ตอบ NO → Blockchain Engineer (ความลึก 7)
                l6_offensive.setNoNode(new Node("Blockchain Engineer",
                                "Develops Smart Contracts and decentralized applications (DApps) on blockchain platforms like Ethereum and Solana. Works with cryptography, consensus algorithms, and Web3 technologies.",
                                "Seme"));

                // ระดับ 5 ตอบ NO → สาย Blockchain / Cybersec สำรอง
                // ระดับ 6
                Node l6_crypto = new Node(
                                "Do you enjoy working with blockchain technology, smart contracts, or decentralized systems?");
                l5_pentest.setNoNode(l6_crypto);

                // ระดับ 6 ตอบ YES → Blockchain Engineer (ความลึก 7)
                l6_crypto.setYesNode(new Node("Blockchain Engineer",
                                "Develops Smart Contracts and decentralized applications (DApps) on blockchain platforms like Ethereum and Solana. Works with cryptography, consensus algorithms, and Web3 technologies.",
                                "Seme"));

                // ระดับ 6 ตอบ NO → Cybersecurity Engineer (ความลึก 7)
                l6_crypto.setNoNode(new Node("Cybersecurity Engineer / Pentester",
                                "Protects organizations by finding and fixing security vulnerabilities. Performs penetration testing, code audits, and red team exercises. Works with tools like Burp Suite, Metasploit, and Wireshark.",
                                "Seme"));

                // ระดับ 4 ตอบ NO → สาย Cloud / Network สำรอง
                // ระดับ 5
                Node l5_cloudOrNet = new Node(
                                "Are you interested in cloud platforms like AWS, Azure, or Google Cloud?");
                l4_security.setNoNode(l5_cloudOrNet);

                // ระดับ 5 ตอบ YES → Cloud Engineer (ความลึก 6)
                l5_cloudOrNet.setYesNode(new Node("Cloud Engineer",
                                "Manages cloud infrastructure on AWS, Azure, or GCP. Provisions virtual machines, networks, and storage. Uses Infrastructure as Code tools like Terraform to automate cloud resource management at scale.",
                                "Uke"));

                // ระดับ 5 ตอบ NO → Network Engineer (ความลึก 6)
                l5_cloudOrNet.setNoNode(new Node("Network Engineer",
                                "Designs, implements, and maintains computer networks including LANs, WANs, and firewalls. Configures routers, switches, and VPNs. Ensures reliable, secure data communication across the organization.",
                                "Uke"));

                // ------ ระดับ 2 ตอบ NO: สายฮาร์ดแวร์/อุปกรณ์ทางกายภาพ (5 อาชีพ) ------

                // ระดับ 3
                Node l3_iot = new Node(
                                "Do you like working with connecting physical devices to the internet or networks?");
                l2_digital.setNoNode(l3_iot);

                // ระดับ 3 ตอบ YES → สาย IoT
                // ระดับ 4
                Node l4_smart = new Node(
                                "Do you like to build smart systems that collect sensor data and automate physical environments?");
                l3_iot.setYesNode(l4_smart);

                // ระดับ 4 ตอบ YES → สาย IoT Protocol
                // ระดับ 5
                Node l5_iotProto = new Node("Do you enjoy working with IoT protocols like MQTT, CoAP, or Zigbee?");
                l4_smart.setYesNode(l5_iotProto);

                // ระดับ 5 ตอบ YES → IoT Engineer (ความลึก 6)
                // (เดิม: "Do you integrate with cloud?" ตอบ YES/NO ไปที่ IoT Eng ทั้งคู่ — ตัดออก)
                l5_iotProto.setYesNode(new Node("IoT Engineer",
                                "Connects physical devices to the Internet to create smart systems. Designs sensor networks, works with IoT protocols (MQTT, CoAP), and integrates devices with cloud platforms for monitoring and automation.",
                                "Uke"));

                // ระดับ 5 ตอบ NO → Embedded Systems Engineer (ความลึก 6)
                l5_iotProto.setNoNode(new Node("Embedded Systems Engineer",
                                "Writes firmware and low-level software for microcontrollers (ARM, ESP32, STM32). Programs hardware at the register level, optimizes for memory and power constraints, and interfaces with sensors and actuators.",
                                "Seme"));

                // ระดับ 4 ตอบ NO → Embedded Systems Engineer (ความลึก 5)
                l4_smart.setNoNode(new Node("Embedded Systems Engineer",
                                "Writes firmware and low-level software for microcontrollers (ARM, ESP32, STM32). Programs hardware at the register level, optimizes for memory and power constraints, and interfaces with sensors and actuators.",
                                "Seme"));

                // ระดับ 3 ตอบ NO: สายฮาร์ดแวร์ล้วน
                // ระดับ 4
                Node l4_circuits = new Node("Do you like to design electronic circuits, PCBs, or work with FPGAs?");
                l3_iot.setNoNode(l4_circuits);

                // ระดับ 4 ตอบ YES → สายฮาร์ดแวร์
                // ระดับ 5
                Node l5_pcb = new Node(
                                "Do you enjoy using CAD tools like Altium, KiCad, or Vivado to design hardware?");
                l4_circuits.setYesNode(l5_pcb);

                // ระดับ 5 ตอบ YES → Hardware Design Engineer (ความลึก 6)
                // (เดิม: "Do you design at chip/FPGA level?" ตอบ YES/NO ไปที่ Hardware ทั้งคู่ — ตัดออก)
                l5_pcb.setYesNode(new Node("Hardware Design Engineer",
                                "Designs electronic circuits, PCBs, FPGAs, and chipsets. Uses CAD tools like Altium Designer and Vivado. Creates schematics, runs simulations, and works with manufacturing to produce physical electronics.",
                                "Seme"));

                // ระดับ 5 ตอบ NO → Embedded Systems Engineer (ความลึก 6)
                l5_pcb.setNoNode(new Node("Embedded Systems Engineer",
                                "Writes firmware and low-level software for microcontrollers (ARM, ESP32, STM32). Programs hardware at the register level, optimizes for memory and power constraints, and interfaces with sensors and actuators.",
                                "Seme"));

                // ระดับ 4 ตอบ NO: ไม่ใช่วงจร
                // ระดับ 5
                Node l5_hwLast = new Node(
                                "Do you like to write low-level firmware for microcontrollers and embedded devices?");
                l4_circuits.setNoNode(l5_hwLast);

                // ระดับ 5 ตอบ YES → Embedded Systems Engineer (ความลึก 6)
                l5_hwLast.setYesNode(new Node("Embedded Systems Engineer",
                                "Writes firmware and low-level software for microcontrollers (ARM, ESP32, STM32). Programs hardware at the register level, optimizes for memory and power constraints, and interfaces with sensors and actuators.",
                                "Seme"));

                // ระดับ 5 ตอบ NO → Hardware Design Engineer (ความลึก 6)
                l5_hwLast.setNoNode(new Node("Hardware Design Engineer",
                                "Designs electronic circuits, PCBs, FPGAs, and chipsets. Uses CAD tools like Altium Designer and Vivado. Creates schematics, runs simulations, and works with manufacturing to produce physical electronics.",
                                "Seme"));

                return new DecisionTree(root);
        }
}