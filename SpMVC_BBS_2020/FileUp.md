# 사진이미지를 업로드하여 이미지 갤러리 BBS로 변환

* spring framework에서는 기본적으로 Text 위주의 project만 지원한다.
* file(종류에 관계 없이)을 upload하기 위해서 apache에서 지원하는 dependency 도움을 받아야 한다.
* commons-io, commons-fileupload

## file upload할수 있도록 서버 context를 설정해 주어야 한다.
* file-context.xml 파일을 설정하여 file upload 설정하는데
* context-param에서 핸들링할수 있도록 root-context.xml 과 같은 위치 작성
* file 선택을 하기 위해 input type="file" tag를 추가하고
* form tag 에 enctype="multipart/form-data"
* upload하는 파일의 type을 제한하고 싶을때 accept="image/*" 설정해 주면 된다.
* 파일의 확장자를 검사하여 제한하고 싶을때 accept=".gif, .jpg, .jpeg, .png"
* 미디어타입과 확장자를 검사하여 제한하고 싶을때 accept="video/mp4, audio/mp3, image/png"

