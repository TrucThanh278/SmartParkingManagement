import classes from './footer.css';

const Footer = () => {
	const currentYear = new Date().getFullYear();

	return (
		<div className = {classes.footer}>
			<footer className = {classes.foot}>
				<span>Coppyright &copy; {currentYear} SmartParking. All Rights Reserved.</span>
			</footer>
		</div>
	);
};

export default Footer;