import React from 'react';
import NaverLogin from 'react-naver-login';

const NaverLoginComponent = ({ onSuccess, onFailure }) => {
    const clientId = 'HQHp_3R0uDH7Ey5eoKgv';

    const NaverLoginSuccess = (response) => {
        onSuccess(response);
    };

    const NaverLoginFailure = (err) => {
        onFailure(err);
    };

    return (
        <NaverLogin
            clientId={clientId}
            callbackUrl="http://localhost:3000/login/oauth2/code/naver"
            onSuccess={NaverLoginSuccess}
            onFailure={NaverLoginFailure}
            render={(props) => (
                <button onClick={props.onClick}>네이버로 로그인</button>
            )}
        />
    );
};

export default NaverLoginComponent;
