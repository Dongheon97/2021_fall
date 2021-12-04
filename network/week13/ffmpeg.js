const ffmpeg = require('fluent-ffmpeg');
const ffmpegInstaller = require('@ffmpeg-installer/ffmpeg');

ffmpeg.setFfmpegPath(ffmpegInstaller.path);

ffmpeg('./radio_file/ebs-test.wav', { timeout: 43200 }).addOptions([
		'-profile:v baseline',
		'-level 3.0',
		'-start_number 0',
		'-hls_time 10',
		'-hls_list_size 0',
		'-f hls',
]).output('radio_file/aa.m3u8').on('end', () => {
}).run();
