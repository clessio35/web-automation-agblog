Projeto Web Automation AG Blog

Automação de testes para o blog do Agibank, utilizando Java 17, Selenium, Cucumber, JUnit e ExtentReports.

- Tecnologias e Dependências

  Java 17
  
  Maven
  
  Selenium 4.4.0
  
  Cucumber 7.34.2
  
  ExtentReports 5.0.9 + Cucumber Adapter
  
  JUnit 4.13.2
  
  Apache POI (para manipulação de Excel caso necessário)

-Estrutura do Projeto

web-automation-agblog/
  src/
    main/
      java/
        config/           # Configurações do driver e setup Selenium
        pages/            # Page Objects (cada página do blog)
        report/           # Configuração do ExtentReports
        utils/            # Métodos utilitários (screenshots, waits, Excel)
      resources/          # Arquivos de teste e propriedades
    test/
      java/
        stepdefinitions/  # Step Definitions do Cucumber
        runners/          # Classes de execução de testes
        hooks/            # Hooks Before/After do Cucumber
  target/
    extent-report/        # Relatórios HTML do ExtentReports
    evidencias/           # Screenshots por cenário
    surefire-reports/     # Relatórios do Maven Surefire
  pom.xml                 # Gerenciador de dependências Maven
  README.md               # Documentação do projeto
  
- Como Executar

  Certifique-se de ter Java 17, Maven e Chrome/ChromeDriver instalados ou webdriver manager.
  
  Clone o repositório:
  
  git clone https://github.com/clessio35/web-automation-agblog.git
  cd web-automation-agblog
  
  Instale as dependências:
  
  mvn clean install
  
  Execute os testes:
  
  mvn test

- Ao final da execução:

 Relatório ExtentReports: target/extent-report/report.html

<img src="https://github.com/user-attachments/assets/4e86ee38-fbf3-404d-bc28-d351bc29e29f" width="600" />

  
  - Screenshots em: evidencias/<nome_cenario>/

- Funcionalidades

  Busca por artigos no blog do Agibank (emprestimos, cartões, pix, caracteres).
  
  Validação de resultados por categoria.
  
  Captura de screenshots e geração de evidências em ExtentReports.

- Observações
  
  Se o teste não encontrar artigos, ele encerra o método com log de aviso e screenshot.
  
  Relatórios ExtentReports podem ser visualizados abrindo o arquivo HTML no navegador.
  
  Evidências são salvas por cenário, garantindo histórico de execução.

- Jenkins

  Configurado localmente, usando a url localhost:8085/
  Lembrar de informar a versão do jdk no jenkins config: 

<img src="https://github.com/user-attachments/assets/28f550b9-17f0-4189-a31a-c6b419a2cf49" width="500" />

Em seguida, execute:

<img src="https://github.com/user-attachments/assets/98fe486c-8d25-4501-bf71-65f1ff0982d5" width="500" />

Visualização final:

<img src="https://github.com/user-attachments/assets/bd18824b-54e1-4738-b026-4713d26a04bb" width="600" />
  
