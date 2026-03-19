Feature: Busca no blog do Agibank via lupa

  Background:
    Given que o usuario acessa o blog do Agibank

  @all @busca_produtos @busca
  Scenario Outline: Busca por produtos principais do banco
    When ele insere "<termo>" na busca
    Then os resultados devem conter conteudos relacionados a "<categoria>"
    Examples:
      | termo                  | categoria           |
      | emprestimo consignado  | emprestimo         |
      | emprestimo pessoal     | emprestimo pessoal |
      | cartao de credito      | cartao             |
      | pix                    | pix                |
      | conta corrente         | conta              |

  @all @relevancia_resultados @relevancia
  Scenario Outline: Validar relevancia dos resultados
    When ele insere "<termo>" na busca
    Then os primeiros resultados devem ser relevantes para "<categoria>"
    And nao deve haver conteudos irrelevantes nas primeiras posicoes

    Examples:
      | termo               | categoria           |
      | emprestimo          | emprestimo         |
      | emprestimo pessoal  | emprestimo pessoal |

  @all @ordenacao_resultados @ordenacao
  Scenario Outline: Validar ordenacao dos resultados
    When ele insere "<termo>" na busca
    Then os resultados devem estar ordenados por "<criterio>"

    Examples:
      | termo               | criterio          |
      | emprestimo pessoal  | data decrescente  |
      | cartao              | consistencia      |

  @all @paginacao_resultados @paginacao
  Scenario: Validar paginacao dos resultados
    When ele insere "pix" na busca
    And acessa a proxima pagina de resultados
    Then novos resultados devem ser exibidos
    And nao deve repetir os resultados anteriores

  @all @abertura_artigo @conteudo
  Scenario: Validar abertura de artigo
    When ele insere "emprestimo pessoal" na busca
    And clica em um dos resultados
    Then o artigo deve ser carregado corretamente
    And o titulo deve corresponder ao resultado clicado

  @all @busca_intencao @semantic
  Scenario Outline: Validar busca por intencao
    When ele insere "<termo>" na busca
    Then os resultados devem ser relevantes para "emprestimo"

    Examples:
      | termo                   |
      | preciso de dinheiro     |
      | como conseguir credito  |
      | dinheiro rapido         |

  @all @busca_caracteres_especiais @robustness
  Scenario Outline: Validar busca com caracteres especiais
    When ele insere "<termo>" na busca
    Then o sistema nao deve apresentar erro
    And os resultados devem ser exibidos corretamente

    Examples:
      | termo          |
      | emprestimo!!!  |
      | cartao@@@      |
      | pix###         |
      | @#$%           |

  @all @entradas_invalidas @edge
  Scenario Outline: Validar entradas invalidas
    When ele insere "<termo>" na busca
    Then o sistema deve tratar a busca corretamente
    And nao deve apresentar falha visual

    Examples:
      | termo                                                                 |
      |                                                                       |
      | a                                                                     |
      | 123456789012345678901234567890123456789012345678901234567890          |