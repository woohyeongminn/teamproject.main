/*import React, { useState } from 'react';
import axios from 'axios';
 
const App () => {
  const [selectedFile, setSelectedFile] = useState(null);
 
  const handleFileChange = (event) => {
    // files 배열 자체를 담을 것이므로 index 뺌.
    setSelectedFile(event.target.files);
  };
 
  const handleFileUpload = () => {
    const formData = new FormData();
 
    // 배열 내부에 있는 모든 요소를 append 해야 하므로
    for (let i = 0; i < selectedFile.length; i++) {
      formData.append("myfile", selectedFile[i], selectedFile[i].name);
    }
 
    // 여러 개의 파일이므로 multipart type으로
    const config = {
      headers: {
        "content-type": "multipart/form-data",
      },
    };
 
    // config 넣어줌
    axios.post("api/uploadfile", formData, config)
      .then(res => {
        console.log(res);
      })
      .catch(err => {
        console.log(err);
      });
  };
 
  return (
    <div>
      <input type="file" onChange={handleFileChange} />
      <button onClick={handleFileUpload}>업로드</button>
    </div>
  );
};
 
export default App;*/



/*<script>
function readImage(input) {
    // 인풋 태그에 파일이 있는 경우
    if(input.files && input.files[0]) {
        // 이미지 파일인지 검사 (생략)
        // FileReader 인스턴스 생성
        const reader = new FileReader()
        // 이미지가 로드가 된 경우
        reader.onload = e => {
            const previewImage = document.getElementById("preview-image")
            previewImage.src = e.target.result
        }
        // reader가 이미지 읽도록 하기
        reader.readAsDataURL(input.files[0])
        
        var formData = new FormData();
        formData.append('img',document.getElementById('f-filepath').files[0]);
    }
}
// input file에 change 이벤트 부여
const inputImage = document.getElementById("f-filepath")
inputImage.addEventListener("change", e => {
    readImage(e.target)
})
</script>*/