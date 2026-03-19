Feature: Busca no blog do Agibank via lupa

  Background:
    Given que o usuario acessa o blog do Agibank

  @all @busca_produtos
  Scenario Outline: Busca por produtos principais do banco
    When ele insere "<termo>" na busca
    Then os resultados devem conter conteudos relacionados a "<categoria>"
    Examples:
      | termo               			     	 | categoria          					 |
      | emprestimo consignado  	 | emprestimo consignado      |
      | emprestimo pessoal 	 		 | emprestimo pessoal			 |
      | cartão de credito    	  		 |  cartão de credito           		  |
      | pix                    					 | pix              							  |
      | conta corrente       				 | conta corrente          	   		   |
	  | cartao@@@    					 | cartao@@@            	   		   |
	   | @#$%     					        |   @#$%     					 		      |
	   | 12345678901234567         |	12345678901234567       |
  