Feature: Busca no blog do Agibank via lupa

  Background:
    Given que o usuario acessa o blog do Agibank
    And clica na lupa de busca

  @all @search @web
  Scenario: Abertura da busca
    Then o campo de busca deve ser exibido corretamente

  @all @search @core @web
  Scenario Outline: Busca por produtos principais do banco
    When ele busca por "<termo>"
    Then os resultados devem conter conteudos relacionados a "<categoria>"
    And pelo menos 3 resultados devem ser exibidos

    Examples:
      | termo                    | categoria           |
      | emprestimo consignado   | emprestimo         |
      | emprestimo pessoal      | emprestimo pessoal |
      | cartao de credito       | cartao             |
      | pix                     | pix                |
      | conta corrente          | conta              |

  @all @search @relevance @web
  Scenario: Validar relevancia dos resultados
    When ele busca por "emprestimo"
    Then os primeiros resultados devem ser relevantes para "emprestimo"
    And nao deve haver conteudos irrelevantes nas primeiras posicoes

  @all @search @ordering @web
  Scenario Outline: Validar ordenacao dos resultados
    When ele busca por "<termo>"
    Then os resultados devem estar ordenados por "<criterio>"

    Examples:
      | termo                | criterio           |
      | emprestimo pessoal  | data decrescente  |
      | cartao              | consistencia      |

  @all @search @pagination @web
  Scenario: Validar paginacao dos resultados
    When ele busca por "pix"
    And acessa a proxima pagina de resultados
    Then novos resultados devem ser exibidos
    And nao deve repetir os resultados anteriores

  @all @search @content @web
  Scenario: Validar abertura de artigo
    When ele busca por "emprestimo pessoal"
    And clica em um dos resultados
    Then o artigo deve ser carregado corretamente
    And o titulo deve corresponder ao resultado clicado

  @all @search @semantic @web
  Scenario Outline: Validar busca por intencao
    When ele busca por "<termo>"
    Then os resultados devem ser relevantes para "emprestimo"

    Examples:
      | termo                   |
      | preciso de dinheiro    |
      | como conseguir credito |
      | dinheiro rapido        |

  @all @search @robustness @web
  Scenario Outline: Validar busca com caracteres especiais
    When ele busca por "<termo>"
    Then o sistema nao deve apresentar erro
    And os resultados devem ser exibidos corretamente

    Examples:
      | termo          |
      | emprestimo!!!  |
      | cartao@@@      |
      | pix###         |
      | @#$%           |

  @all @search @edge @web
  Scenario Outline: Validar entradas invalidas
    When ele busca por "<termo>"
    Then o sistema deve tratar a busca corretamente
    And nao deve apresentar falha visual

    Examples:
      | termo                                                                 |
      |                                                                       |
      | a                                                                     |
      | 123456789012345678901234567890123456789012345678901234567890          |