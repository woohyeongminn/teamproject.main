import React, { useState } from 'react';
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
 
export default App;