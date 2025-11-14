const mysql = require('mysql2/promise');

async function setupWorkspace() {
  const connection = await mysql.createConnection({
    host: 'project-db-campus.smhrd.com',
    port: 3312,
    user: 'mp_24K_AI5_p3_1',
    password: 'smhrd1',
    database: 'mp_24K_AI5_p3_1'
  });

  try {
    // 1. workspace_items 테이블 구조 확인
    console.log('=== workspace_items 테이블 구조 ===');
    const [tableInfo] = await connection.execute('DESCRIBE workspace_items');
    console.table(tableInfo);

    // 2. 모든 tool_id 조회
    console.log('\n=== 사용 가능한 tools ===');
    const [tools] = await connection.execute('SELECT tool_id, tool_name FROM tools');
    console.table(tools);

    // 3. 랜덤으로 5개의 tool_id 선택
    const shuffled = [...tools].sort(() => 0.5 - Math.random());
    const selectedTools = shuffled.slice(0, Math.min(5, tools.length));

    console.log('\n=== 선택된 tools (랜덤 5개) ===');
    console.table(selectedTools);

  } catch (error) {
    console.error('Error:', error.message);
  } finally {
    await connection.end();
  }
}

setupWorkspace();
