/**
 * Função para realizar o login na API
 * 
 * @param {string} account - O login/conta do usuário.
 * @param {string} password - A senha do usuário.
 * @returns {Promise<void>}
 */
async function realizarLogin(account, password) {
    const url = 'http://localhost:8080/api/login';
    const payload = {
        account: account,
        password: password
    };

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload),
            credentials: 'include' // Necessário para enviar/receber cookies HttpOnly
        });

        if (response.status === 401) {
            alert('Credenciais inválidas! Por favor, tente novamente.');
            return;
        }

        if (!response.ok) {
            throw new Error('Ocorreu um erro inesperado ao tentar realizar o login.');
        }

        const data = await response.json();

        // Com o uso de cookies HttpOnly, o navegador gerencia o token automaticamente.
        // Não é mais necessário salvar manualmente no localStorage.
        console.log('Login realizado com sucesso! O token foi armazenado em um cookie seguro.');
        // Opcional: redirecionar o usuário ou atualizar a página

    } catch (error) {
        console.error('Erro:', error.message);
        alert(error.message);
    }
}



