const mysql = require('mysql2/promise');

async function insertWorkspaceItems() {
  const connection = await mysql.createConnection({
    host: 'project-db-campus.smhrd.com',
    port: 3312,
    user: 'mp_24K_AI5_p3_1',
    password: 'smhrd1',
    database: 'mp_24K_AI5_p3_1'
  });

  try {
    // 0. workspaces 테이블 확인 및 workspace 생성
    console.log('=== workspaces 테이블 구조 ===');
    const [wsTableInfo] = await connection.execute('DESCRIBE workspaces');
    console.table(wsTableInfo);

    console.log('\n=== 기존 workspaces 조회 ===');
    const [existingWorkspaces] = await connection.execute('SELECT * FROM workspaces');
    console.table(existingWorkspaces);

    // workspaces 테이블이 비어있으면 테스트용 workspace 생성
    let workspaceId;
    if (existingWorkspaces.length === 0) {
      console.log('\n=== 테스트용 workspace 생성 ===');
      const [wsResult] = await connection.execute(
        'INSERT INTO workspaces (user_id, workspace_name, updated_at) VALUES (?, ?, CURRENT_TIMESTAMP)',
        [1, '테스트 워크스페이스']
      );
      workspaceId = wsResult.insertId;
      console.log(`✓ workspace_id ${workspaceId} 생성 완료`);
    } else {
      workspaceId = existingWorkspaces[0].workspace_id;
      console.log(`\n✓ 기존 workspace_id ${workspaceId} 사용`);
    }

    // 1. workspace_items 테이블 구조 확인
    console.log('\n=== workspace_items 테이블 구조 ===');
    const [tableInfo] = await connection.execute('DESCRIBE workspace_items');
    console.table(tableInfo);

    // 2. tools 테이블에서 모든 tool_id 조회
    console.log('\n=== tools 테이블에서 모든 tool_id 조회 ===');
    const [tools] = await connection.execute('SELECT tool_id, tool_name FROM tools');
    console.table(tools);

    // 3. 랜덤으로 5개 선택
    const shuffled = [...tools].sort(() => 0.5 - Math.random());
    const selectedTools = shuffled.slice(0, Math.min(5, tools.length));

    console.log('\n=== 랜덤으로 선택된 5개 tools ===');
    console.table(selectedTools);

    // 4. workspace_items에 삽입
    console.log(`\n=== workspace_id ${workspaceId}에 삽입 시작 ===`);

    for (const tool of selectedTools) {
      const [result] = await connection.execute(
        'INSERT INTO workspace_items (workspace_id, tool_id, updated_at) VALUES (?, ?, CURRENT_TIMESTAMP)',
        [workspaceId, tool.tool_id]
      );
      console.log(`✓ tool_id ${tool.tool_id} (${tool.tool_name}) 삽입 완료 - workspace_item_id: ${result.insertId}`);
    }

    // 5. 삽입된 데이터 확인
    console.log('\n=== 삽입된 workspace_items 확인 ===');
    const [items] = await connection.execute(
      'SELECT * FROM workspace_items WHERE workspace_id = ?',
      [workspaceId]
    );
    console.table(items);

    console.log('\n✅ 워크스페이스 생성 완료!');

  } catch (error) {
    console.error('❌ Error:', error.message);
  } finally {
    await connection.end();
  }
}

insertWorkspaceItems();
