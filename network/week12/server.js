const express = require('express')
const app = express();
const server = require('http').Server(app);
const io = require('socket.io')(server);
const { v4: uuidV4 } = require('uuid');

app.set('view engine', 'ejs');
app.use(express.static('public'));

app.get('/', (req, res) => {
		res.redirect(`/${uuidV4()}`);	// uuidV4 함수를 사용하여 uuid를 얻음
		// 임의로 생성한 uuid -> Room을 의미함
});

app.get('/:room', (req, res) => {
		res.render('room', { roomId: req.params.room });
});

io.on('connection', socket => {
		socket.on('join-room', (roomId, userId) => {
				socket.join(roomId);
				socket.to(roomId).broadcast.emit('user-conntected', userId);

				socket.on('disconnect', () => {
						socket.to(roomId).broadcast.emit('user-disconnected', userId);
				});
		});
});

server.listen(3000);
