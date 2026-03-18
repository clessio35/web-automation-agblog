Feature: Busca no blog do Agibank via lupa

  @all @search @web
  Scenario: Acesso ao blog e abertura da busca
    Given que o usuario acessa o blog do Agibank
    When ele clica na lupa de busca
    Then o campo de busca deve ser exibido corretamente

  @all @search @core @web
  Scenario Outline: Busca por produtos principais do banco
    Given que o usuario acessa o blog do Agibank
    And clica na lupa de busca
    When ele busca por "<termo>"
    Then os resultados devem conter conteudos relacionados a "<categoria>"
    And pelo menos 3 resultados devem ser exibidos

    Examples:
      | termo                    | categoria              |
      | emprestimo consignado   | emprestimo            |
      | emprestimo pessoal      | emprestimo pessoal    |
      | cartao de credito       | cartao                |
      | pix                     | pix                   |
      | conta corrente          | conta                 |

  @all @search @relevance @web
  Scenario: Validar relevancia dos resultados para emprestimo
    Given que o usuario acessa o blog do Agibank
    And clica na lupa de busca
    When ele busca por "emprestimo"
    Then os 3 primeiros resultados devem conter termos relacionados a emprestimo
    And nao deve haver conteudos irrelevantes nas primeiras posicoes

  @all @search @ordering @web
  Scenario: Validar ordenacao por data para busca especifica
    Given que o usuario acessa o blog do Agibank
    And clica na lupa de busca
    When ele busca por "emprestimo pessoal"
    Then os resultados devem estar ordenados por data decrescente

  @all @search @inconsistency @web
  Scenario: Validar consistencia de ordenacao para cartao
    Given que o usuario acessa o blog do Agibank
    And clica na lupa de busca
    When ele busca por "cartao"
    Then os resultados devem manter um criterio consistente de ordenacao
    And nao devem exibir conteudos muito antigos antes dos mais recentes

  @all @search @pagination @web
  Scenario: Validar paginacao dos resultados
    Given que o usuario acessa o blog do Agibank
    And clica na lupa de busca
    When ele busca por "pix"
    And acessa a proxima pagina de resultados
    Then novos resultados devem ser exibidos
    And nao deve repetir os resultados da pagina anterior

  @all @search @content @web
  Scenario: Validar abertura de artigo a partir da busca
    Given que o usuario acessa o blog do Agibank
    And clica na lupa de busca
    When ele busca por "emprestimo pessoal"
    And clica em um dos resultados
    Then o artigo deve ser carregado corretamente
    And o titulo deve corresponder ao resultado clicado

  @all @search @semantic @web
  Scenario Outline: Validar busca por termos relacionados (intencao)
    Given que o usuario acessa o blog do Agibank
    And clica na lupa de busca
    When ele busca por "<termo>"
    Then os resultados devem conter conteudos relacionados a emprestimo

    Examples:
      | termo                  |
      | preciso de dinheiro   |
      | como conseguir credito|
      | dinheiro rapido       |

  @all @search @robustness @web
  Scenario Outline: Validar busca com caracteres especiais
    Given que o usuario acessa o blog do Agibank
    And clica na lupa de busca
    When ele busca por "<termo>"
    Then o sistema nao deve apresentar erro
    And os resultados devem ser exibidos de forma controlada

    Examples:
      | termo              |
      | emprestimo!!!     |
      | cartao@@@         |
      | pix###            |
      | @#$%              |

  @all @search @edge @web
  Scenario Outline: Validar busca com entradas invalidas
    Given que o usuario acessa o blog do Agibank
    And clica na lupa de busca
    When ele busca por "<termo>"
    Then o sistema deve tratar a busca corretamente
    And nao deve apresentar falha ou quebra de layout

    Examples:
      | termo                                                                 |
      |                                                                       |
      | a                                                                     |
      | 123456789012345678901234567890123456789012345678901234567890          |