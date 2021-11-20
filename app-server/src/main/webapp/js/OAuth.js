const CLIENT_ID = "624ecc54370fb53151eb02f2ed28f460";
const REDIRECT_URI =  "http://localhost:8080/ogong/app/login";

export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;