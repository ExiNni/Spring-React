const handleNaverLogin = async (req, res) => {
    try {

        res.status(200).json({ message: 'Naver login successful' });
    } catch (error) {
        res.status(500).json({ error: 'Naver login error', details: error.message });
    }
};

export default handleNaverLogin;
