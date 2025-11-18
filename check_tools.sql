-- 데이터베이스의 tool_name 확인용 SQL
-- MySQL Workbench나 다른 DB 클라이언트에서 실행하세요

USE mp_24K_AI5_p3_1;

-- 모든 tool_name 조회 (정렬)
SELECT tool_id, tool_name, category_id
FROM tools
ORDER BY tool_name;

-- tool_name 개수 확인
SELECT COUNT(*) as total_tools FROM tools;

-- 카테고리별 tool 개수
SELECT category_id, COUNT(*) as tool_count
FROM tools
GROUP BY category_id;
