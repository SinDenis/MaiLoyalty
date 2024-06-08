import {
  Button,
  Card,
  CardActions,
  CardContent,
  Chip,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  IconButton,
  Snackbar,
  Typography
} from "@mui/material";
import React, {useState} from "react";
import {Promotion} from "./Main";
import axios from "axios";

function CloseIcon(props: { fontSize: string }) {
  return null;
}

const PromoItem: React.FC<Promotion> = (props) => {

  const [open, setOpen] = useState(false);
  const [code, setCode] = useState<string | null>(null)
  const [notificationOpen, setNotificationOpen] = useState<boolean>(false)

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const action = (
    <React.Fragment>
      <Button color="secondary" size="small" onClick={() => setNotificationOpen(false)}>
        UNDO
      </Button>
      <IconButton
        size="small"
        aria-label="close"
        color="inherit"
        onClick={handleClose}
      >
        <CloseIcon fontSize="small" />
      </IconButton>
    </React.Fragment>
  );

  const applyCode = () => {
    console.log(code, props)
    axios.post(`http://localhost:8080/api/personal-account/promotions/${props.id}/validate`, {code: code}, {withCredentials: true})
      .then(response => {
        console.log('hello')
        if (response.status === 200) {
          setNotificationOpen(true)
        }
      })
  }

  return (
    <Card className="center" sx={{
      width: 300,
      mb: '20px',
      height: 200,
      borderRadius: "15px",
      background: "#e8f5f8",
      boxShadow: "3px 3px"
    }}>
      <CardContent sx={{height: "50%"}}>
        <Typography gutterBottom component="div">
          {props.title}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {props.description}
        </Typography>
      </CardContent>
      <Chip color="primary" label={props.category} size="small"/>
      <CardActions sx={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
        <Button variant='outlined' onClick={handleClickOpen}>Валидировать</Button>
        <Button variant='outlined'>Статистика</Button>
        <Dialog
          open={open}
          onClose={handleClose}
          PaperProps={{
            component: 'form',
            onSubmit: (event: React.FormEvent<HTMLFormElement>) => {
              event.preventDefault();
              const formData = new FormData(event.currentTarget);
              const formJson = Object.fromEntries((formData as any).entries());
              const email = formJson.email;
              console.log(email);
              handleClose();
            },
          }}
        >
          <DialogTitle>Применение кода</DialogTitle>
          <DialogContent>
            <DialogContentText>
              Введите код, который скажет клиент
            </DialogContentText>
            <input id="description"
                   type="text"
                   name="description"
                   onChange={(e) => setCode(e.target.value)}
            />
          </DialogContent>
          <DialogActions>
            <Button onClick={handleClose}>Отмена</Button>
            <Button onClick={applyCode}>Применить</Button>
          </DialogActions>
        </Dialog>
        <Snackbar
          anchorOrigin={ {vertical: 'top', horizontal: 'center'} }
          open={notificationOpen}
          autoHideDuration={6000}
          onClose={handleClose}
          message="Клиент успешно применил акцию"
          action={action}
        />
      </CardActions>
    </Card>
  )
};

export default PromoItem;
