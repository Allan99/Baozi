INSERT INTO tb_cliente (nome, cliente_desde) VALUES ('João Silva', '2025-05-15');
INSERT INTO tb_cliente (nome, cliente_desde) VALUES ('Maria Oliveira', '2026-01-10');
INSERT INTO tb_cliente (nome, cliente_desde) VALUES ('Carlos Eduardo Santos', '2024-11-22');
INSERT INTO tb_cliente (nome, cliente_desde) VALUES ('Ana Beatriz Costa', '2025-08-03');
INSERT INTO tb_cliente (nome, cliente_desde) VALUES ('Lucas Pereira Lima', '2026-03-19');

INSERT INTO tb_produto (nome, preco, em_estoque) VALUES ('Pão caseiro', 14.00, true);

INSERT INTO tb_pedido (produto_id, cliente_id, quantidade) VALUES (1, 2, 5);
INSERT INTO tb_pedido (produto_id, cliente_id, quantidade) VALUES (1, 3, 2);