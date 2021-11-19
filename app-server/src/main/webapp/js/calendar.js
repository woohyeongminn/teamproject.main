let date = new Date();

  const renderCalender = () => {
  const viewYear = date.getFullYear();
  const viewMonth = date.getMonth();

  if (viewMonth  == 0) {
    var month = "Jan.";
  }
  else if (viewMonth  == 1) {
    var month = "Feb.";
  }
  else if (viewMonth  == 2) {
    var month = "Mar.";
  }
  else if (viewMonth  == 3) {
    var month = "Apr.";
  }
  else if (viewMonth  == 4) {
    var month = "May.";
  }
  else if (viewMonth  == 5) {
   var month = "Jun.";
  }  
  else if (viewMonth  == 6) {
   var month = "Jul.";
  }
  else if (viewMonth  == 7) {
   var month = "Aug.";
  }
  else if (viewMonth  == 8) {
   var month = "Sep.";
  }
  else if (viewMonth  == 9) {
   var month = "Oct.";
  }
  else if (viewMonth  == 10) {
   var month = "Nov.";
  }
  else if (viewMonth  == 11) {
   var month = "Dec.";
  }
  
  document.querySelector('.year-month').textContent = `${month} ${viewYear}`;

  const prevLast = new Date(viewYear, viewMonth, 0);
  const thisLast = new Date(viewYear, viewMonth + 1, 0);

  const PLDate = prevLast.getDate();
  const PLDay = prevLast.getDay();

  const TLDate = thisLast.getDate();
  const TLDay = thisLast.getDay();

  const prevDates = [];
  const thisDates = [...Array(TLDate + 1).keys()].slice(1);
  const nextDates = [];

  if (PLDay !== 6) {
    for (let i = 0; i < PLDay + 1; i++) {
      prevDates.unshift(PLDate - i);
    }
  }

  for (let i = 1; i < 7 - TLDay; i++) {
    nextDates.push(i);
  }

  const dates = prevDates.concat(thisDates, nextDates);
  const firstDateIndex = dates.indexOf(1);
  const lastDateIndex = dates.lastIndexOf(TLDate);

  dates.forEach((date, i) => {
    const condition = i >= firstDateIndex && i < lastDateIndex + 1
                      ? 'this'
                      : 'other';
    dates[i] = `<div class="date"><span class=${condition}>${date}</span></div>`;
  });

  document.querySelector('.dates').innerHTML = dates.join('');

  const today = new Date();
  if (viewMonth === today.getMonth() && viewYear === today.getFullYear()) {
    for (let date of document.querySelectorAll('.this')) {
      if (+date.innerText === today.getDate()) {
        date.classList.add('today');
        break;
      }
    }
  }
};

renderCalender();

const prevMonth = () => {
  //date.setDate(1);
  date.setMonth(date.getMonth() - 1);
  renderCalender();
};

const nextMonth = () => {
  //date.setDate(1);
  date.setMonth(date.getMonth() + 1);
  renderCalender();
};

const goToday = () => {
  date = new Date();
  renderCalender();
};

