// services/fetch.tsx

const baseURL = 'https://api.example.com';

const fetchInstance = async (endpoint: string, method: string = 'GET', data: any = null, token: string | null = null) => {
    const url = `${baseURL}/${endpoint}`;

    const headers: HeadersInit = {
        'Content-Type': 'application/json',
        // Other headers as needed
    };

    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }

    const options: RequestInit = {
        method,
        headers,
        body: data ? JSON.stringify(data) : null,
    };

    try {
        const response = await fetch(url, options);

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        return await response.json();
    } catch (error) {
        console.error('Fetch error:', error);
        throw error;
    }
};

export default fetchInstance;
