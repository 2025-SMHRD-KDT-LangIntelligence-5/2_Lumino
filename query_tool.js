const mysql = require('mysql2/promise');

async function queryDatabase() {
  const connection = await mysql.createConnection({
    host: 'project-db-campus.smhrd.com',
    port: 3312,
    user: 'mp_24K_AI5_p3_1',
    password: 'smhrd1',
    database: 'mp_24K_AI5_p3_1'
  });

  try {
    const [rows] = await connection.execute(
      'SELECT tool_name FROM tools WHERE tool_id = ?',
      [1]
    );

    if (rows.length > 0) {
      console.log('tool_name:', rows[0].tool_name);
    } else {
      console.log('No data found for tool_id = 1');
    }
  } catch (error) {
    console.error('Error:', error.message);
  } finally {
    await connection.end();
  }
}

queryDatabase();
